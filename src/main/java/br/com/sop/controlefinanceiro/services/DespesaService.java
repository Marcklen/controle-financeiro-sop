package br.com.sop.controlefinanceiro.services;

import java.util.List;

import br.com.sop.controlefinanceiro.domain.Despesa;

public interface DespesaService {

	List<Despesa> findAll();
}
