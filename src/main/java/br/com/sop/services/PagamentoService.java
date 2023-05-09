package br.com.sop.services;

import br.com.sop.entities.EmpenhoEntity;
import br.com.sop.entities.PagamentoEntity;
import br.com.sop.entities.dtos.in.PagamentoCreateDTO;
import br.com.sop.entities.dtos.out.PagamentoDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.PagamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ObjectMapper objectMapper;
    private final EmpenhoService empenhoService;

    public PagamentoDTO criarPagamento(Integer idEmpenho, PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException {
        try {
            EmpenhoEntity empenhoEntity = empenhoService.buscarEmpenhoPorId(idEmpenho);
            if (empenhoEntity == null) {
                throw new RegraDeNegocioException("Empenho não encontrado para o id informado.");
            }
            PagamentoEntity pagamentoEntity = objectMapper.convertValue(pagamentoCreateDTO, PagamentoEntity.class);
            // SETANDO O ID DO EMPENHO
            pagamentoEntity.setEmpenho(empenhoEntity);
            PagamentoEntity pagamentoEntityCriado = pagamentoRepository.save(pagamentoEntity);
            return objectMapper.convertValue(pagamentoEntityCriado, PagamentoDTO.class);
        } catch (Exception e) {
            log.error("Erro ao criar pagamento", e);
            throw new RegraDeNegocioException("Erro ao criar pagamento");
        }
    }

    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamento -> objectMapper.convertValue(pagamento, PagamentoDTO.class))
                .toList();
    }

    public PagamentoEntity buscarPagamentoPorId(Integer idPagamento) throws RegraDeNegocioException {
        return pagamentoRepository.findById(idPagamento)
                .orElseThrow(() -> new RegraDeNegocioException("Pagamento não encontrado"));
    }
}
