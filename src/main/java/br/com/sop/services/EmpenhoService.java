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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmpenhoService {

    private final EmpenhoRepository empenhoRepository;
    private final ObjectMapper objectMapper;
    private final DespesaService despesaService;

    public EmpenhoDTO criarEmpenho(Integer idDespesa, EmpenhoCreateDTO empenhoCreateDTO) throws RegraDeNegocioException {
        try {
            DespesaEntity despesaEntity = despesaService.buscarDespesaPorId(idDespesa);
            EmpenhoEntity empenhoEntity = objectMapper.convertValue(empenhoCreateDTO, EmpenhoEntity.class);
            // SETAR O ID DA DESPESA
            empenhoEntity.setDespesa(despesaEntity);
            EmpenhoEntity empenhoEntityCriado = empenhoRepository.save(empenhoEntity);
            return objectMapper.convertValue(empenhoEntityCriado, EmpenhoDTO.class);
        } catch (Exception e) {
            log.error("Erro ao criar empenho", e);
            throw new RegraDeNegocioException("Erro ao criar empenho");
        }
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
