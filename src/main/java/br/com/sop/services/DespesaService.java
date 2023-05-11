package br.com.sop.services;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.dtos.out.DespesaDTO;
import br.com.sop.entities.enums.StatusDespesa;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.DespesaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final ObjectMapper objectMapper;

    public DespesaDTO criarDespesa(DespesaCreateDTO despesaCreateDTO) throws RegraDeNegocioException {
        try {
            DespesaEntity despesaEntity = objectMapper.convertValue(despesaCreateDTO, DespesaEntity.class);
            // REGRA DE NEGOCIA CRIADA EM QUE TODA VIDA QUE SE CRIA UMA DESPESA O STATUS DELA É DE AGUARDANDO_EMPENHO(1)
            despesaEntity.setTipo_despesa(StatusDespesa.AGUARDANDO_EMPENHO);
            DespesaEntity despesaEntityCriada = despesaRepository.save(despesaEntity);
            return objectMapper.convertValue(despesaEntityCriada, DespesaDTO.class);
        } catch (Exception e) {
            log.error("Erro ao criar despesa", e);
            throw new RegraDeNegocioException("Erro ao criar despesa");
        }
    }

    public List<DespesaDTO> listarDespesas() {
        return despesaRepository.findAll()
                .stream()
                .map(despesa -> objectMapper.convertValue(despesa, DespesaDTO.class))
                .toList();
    }

    public List<DespesaDTO> listarDespesasPorDataProtocolo(LocalDate dataInicio, LocalDate dataFim) {
        return despesaRepository
                .findByData_protocoloBetween(dataInicio, dataFim)
                .stream()
                .map(despesa -> objectMapper.convertValue(despesa, DespesaDTO.class))
                .toList();
    }

    public List<DespesaDTO> listarDespesaPorStatus(StatusDespesa statusDespesa) {
        return despesaRepository
                .findByTipo_despesaExists(statusDespesa)
                .stream()
                .map(despesa -> objectMapper.convertValue(despesa, DespesaDTO.class))
                .toList();
    }

    public List<DespesaDTO> listarDespesaPorCredor(String credor) {
        return despesaRepository
                .findDistinctByCredor_despesaContainingIgnoreCase(credor)
                .stream()
                .map(despesa -> objectMapper.convertValue(despesa, DespesaDTO.class))
                .toList();
    }

    public DespesaEntity buscarDespesaPorId(Integer idDespesa) throws RegraDeNegocioException {
        return despesaRepository.findById(idDespesa)
                .orElseThrow(() -> new RegraDeNegocioException("Despesa não encontrada"));
    }
}
