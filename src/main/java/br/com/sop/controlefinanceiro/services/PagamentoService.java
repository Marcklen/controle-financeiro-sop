package br.com.sop.controlefinanceiro.services;

import org.springframework.stereotype.Service;

import br.com.sop.controlefinanceiro.domain.Pagamento;
import br.com.sop.controlefinanceiro.repositories.PagamentoRepository;
import br.com.sop.controlefinanceiro.services.impl.ServiceMaster;

@Service
public class PagamentoService extends ServiceMaster<Pagamento, PagamentoRepository> {

}
