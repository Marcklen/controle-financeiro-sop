package br.com.sop.services;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.EmpenhoEntity;
import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import br.com.sop.entities.dtos.out.EmpenhoDTO;
import br.com.sop.entities.dtos.out.PageDTO;
import br.com.sop.entities.enums.StatusDespesa;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.EmpenhoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmpenhoService {

    private final EmpenhoRepository empenhoRepository;
    private final ObjectMapper objectMapper;
    private final DespesaService despesaService;

    public EmpenhoDTO criarEmpenho(Integer idDespesa, EmpenhoCreateDTO empenhoCreateDTO) throws RegraDeNegocioException {
        DespesaEntity despesaEntity = despesaService.buscarDespesaPorId(idDespesa);
        if (despesaEntity == null) {
            throw new RegraDeNegocioException("Despesa não encontrada para o id informado.");
        }
        EmpenhoEntity empenhoEntity = objectMapper.convertValue(empenhoCreateDTO, EmpenhoEntity.class);
        // SETAR O ID DA DESPESA
        empenhoEntity.setId_despesa(idDespesa);
        empenhoEntity.setDespesa(despesaEntity);
        // SETAR O EMPENHO NA DESPESA
        empenhoEntity.getDespesa().getEmpenhos().add(empenhoEntity);
        // VERIFICAÇÕES COM O VALOR DO EMPENHO SE É VÁLIDO PARA A DESPESA
        verificarValorEmpenho(idDespesa, empenhoEntity.getValor_empenho());
        // SALVAR O EMPENHO
        EmpenhoEntity empenhoEntityCriado = empenhoRepository.save(empenhoEntity);
        return objectMapper.convertValue(empenhoEntityCriado, EmpenhoDTO.class);
    }

    public List<EmpenhoDTO> listarEmpenhos() {
        return empenhoRepository.findAll()
                .stream()
                .map(empenho -> objectMapper.convertValue(empenho, EmpenhoDTO.class))
                .toList();
    }

    public PageDTO<EmpenhoDTO> listaPaginadaFiltradaPorDataInicioEDataFim(Integer pagina,
                                                                          Integer tamanho,
                                                                          LocalDate dataInicio,
                                                                          LocalDate dataFim) {
        Pageable solicitaoPagina = PageRequest.of(pagina, tamanho);

        Page<EmpenhoEntity> paginacaoPorDatas =
                empenhoRepository.findByData_empenhoBetween(solicitaoPagina, dataInicio, dataFim);

        List<EmpenhoDTO> empenhoDTOList = paginacaoPorDatas
                .getContent()
                .stream()
                .map(empenho -> objectMapper.convertValue(empenho, EmpenhoDTO.class))
                .toList();

        return new PageDTO<>(
                paginacaoPorDatas.getTotalElements(),
                paginacaoPorDatas.getTotalPages(),
                pagina,
                tamanho,
                empenhoDTOList
        );
    }

    public EmpenhoEntity buscarEmpenhoPorId(Integer idEmpenho) throws RegraDeNegocioException {
        return empenhoRepository.findById(idEmpenho)
                .orElseThrow(() -> new RegraDeNegocioException("Empenho não encontrado"));
    }


    // método para verificar se o valor do empenho é válido para a despesa
    private boolean verificarValorEmpenho(Integer idDespesa, Double valorEmpenho) throws RegraDeNegocioException {
        DespesaEntity despesaEntity = despesaService.buscarDespesaPorId(idDespesa);
        if (despesaEntity == null) {
            throw new RegraDeNegocioException("Despesa não encontrada para o id informado.");
        }
        Double valorDaDespesa = despesaEntity.getValor_despesa();
        Double valorEmpenhado = despesaEntity
                .getEmpenhos()
                .stream()
                .mapToDouble(EmpenhoEntity::getValor_empenho).sum();
        Double valorRestante = valorDaDespesa - valorEmpenhado;
        // modificar o status conforme os valores
        if (valorEmpenhado == 0 || valorEmpenhado > 0 && valorEmpenhado < valorDaDespesa) {
            despesaEntity.setTipo_despesa(StatusDespesa.PARCIALMENTE_EMPENHADA);
        } else if (valorEmpenhado.equals(valorDaDespesa)) {
            despesaEntity.setTipo_despesa(StatusDespesa.AGUARDANDO_PAGAMENTO);
        } else if (valorEmpenhado > valorDaDespesa) {
            throw new RegraDeNegocioException("O Valor do empenho não pode ser maior que o valor da despesa.");
        }
        return valorRestante >= valorEmpenho;
    }
}
