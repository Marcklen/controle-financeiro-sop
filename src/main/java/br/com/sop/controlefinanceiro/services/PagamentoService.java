package br.com.sop.controlefinanceiro.services;

import java.util.List;

import br.com.sop.controlefinanceiro.domain.Pagamento;

public interface PagamentoService {

	List<Pagamento> findAll();
	Pagamento create (Pagamento pagamento);
}
