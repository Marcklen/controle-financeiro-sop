package br.com.sop.controlefinanceiro.services;

import java.util.List;

import br.com.sop.controlefinanceiro.domain.Empenho;

public interface EmpenhoService {

	List<Empenho> findAll();
	Empenho create(Empenho empenho);
}
