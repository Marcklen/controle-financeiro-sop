package br.com.sop.services;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.EmpenhoEntity;
import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import br.com.sop.entities.dtos.out.EmpenhoDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.EmpenhoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public EmpenhoEntity buscarEmpenhoPorId(Integer idEmpenho) throws RegraDeNegocioException {
        return empenhoRepository.findById(idEmpenho)
                .orElseThrow(() -> new RegraDeNegocioException("Empenho não encontrado"));
    }
}
