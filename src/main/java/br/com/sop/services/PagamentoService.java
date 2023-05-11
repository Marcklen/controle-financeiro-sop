package br.com.sop.services;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.EmpenhoEntity;
import br.com.sop.entities.PagamentoEntity;
import br.com.sop.entities.dtos.in.PagamentoCreateDTO;
import br.com.sop.entities.dtos.out.PagamentoDTO;
import br.com.sop.entities.enums.StatusDespesa;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.repositories.PagamentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ObjectMapper objectMapper;
    private final EmpenhoService empenhoService;

    public PagamentoDTO criarPagamento(Integer idEmpenho, PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException {
        EmpenhoEntity empenhoEntity = empenhoService.buscarEmpenhoPorId(idEmpenho);
        if (empenhoEntity == null) {
            throw new RegraDeNegocioException("Empenho não encontrado para o id informado.");
        }
        PagamentoEntity pagamentoEntity = objectMapper.convertValue(pagamentoCreateDTO, PagamentoEntity.class);
        // SETANDO O ID DO EMPENHO
        pagamentoEntity.setId_empenho(idEmpenho);
        pagamentoEntity.setEmpenho(empenhoEntity);
        // SETAR O PAGAMENTO NO EMPENHO
        pagamentoEntity.getEmpenho().getPagamentos().add(pagamentoEntity);
        // VERIFICAÇÕES COM O VALOR DO PAGAMENTO SE É VÁLIDO PARA O EMPENHO
        verificarValorDoPagamento(idEmpenho, pagamentoEntity.getValor_pagamento());
        // SALVAR O PAGAMENTO
        PagamentoEntity pagamentoEntityCriado = pagamentoRepository.save(pagamentoEntity);
        return objectMapper.convertValue(pagamentoEntityCriado, PagamentoDTO.class);
    }

    public List<PagamentoDTO> listarPagamentos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(pagamento -> objectMapper.convertValue(pagamento, PagamentoDTO.class))
                .toList();
    }

    public List<PagamentoDTO> listarPagamentoComFiltroDataInicioEDataFim(LocalDate dataInicio, LocalDate dataFim) {
        return pagamentoRepository
                .findByData_pagamentoBetween(dataInicio, dataFim)
                .stream()
                .map(pagamento -> objectMapper.convertValue(pagamento, PagamentoDTO.class))
                .toList();
    }

    // método para excluir um pagamento que não tenha um empenho cadastrado
    public void excluirPagamento(Integer idPagamento) throws RegraDeNegocioException {
        PagamentoEntity pagamentoEntity = buscarPagamentoPorId(idPagamento);
        if (pagamentoEntity.getEmpenho().getId_empenho() == null) {
            pagamentoRepository.deleteById(idPagamento);
        } else {
            throw new RegraDeNegocioException("Não é possível excluir um pagamento que tenha um empenho cadastrado.");
        }
    }

    public PagamentoEntity buscarPagamentoPorId(Integer idPagamento) throws RegraDeNegocioException {
        return pagamentoRepository.findById(idPagamento)
                .orElseThrow(() -> new RegraDeNegocioException("Pagamento não encontrado"));
    }

    // MÉTODO PARA VERIFICAR SE O VALOR DO PAGAMENTO É VÁLIDO PARA O EMPENHO
    private boolean verificarValorDoPagamento(Integer idEmpenho, Double valorPagamento) throws RegraDeNegocioException {
        EmpenhoEntity empenhoEntity = empenhoService.buscarEmpenhoPorId(idEmpenho);
        if (empenhoEntity == null) {
            throw new RegraDeNegocioException("Empenho não encontrado para o id informado.");
        }
        DespesaEntity despesaEntity = empenhoEntity.getDespesa();
        Double valorDoEmpenho = empenhoEntity.getValor_empenho();
        valorPagamento = empenhoEntity
                .getPagamentos()
                .stream()
                .mapToDouble(PagamentoEntity::getValor_pagamento).sum();
        Double valorRestante = valorDoEmpenho - valorPagamento;
        // modificar o status dos empenhos
        if (valorPagamento.equals(0d) || valorPagamento > 0 && valorPagamento < valorDoEmpenho) {
            despesaEntity.setTipo_despesa(StatusDespesa.PARCIALMENTE_PAGA);
        } else if (valorPagamento.equals(valorDoEmpenho)) {
            despesaEntity.setTipo_despesa(StatusDespesa.PAGA);
        } else if (valorPagamento > valorDoEmpenho) {
            throw new RegraDeNegocioException("O Valor do pagamento não pode ser maior que o valor do empenho.");
        }
        return valorRestante >= valorPagamento;
    }
}
