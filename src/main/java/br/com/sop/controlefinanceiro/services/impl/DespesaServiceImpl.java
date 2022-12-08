package br.com.sop.controlefinanceiro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sop.controlefinanceiro.domain.Despesa;
import br.com.sop.controlefinanceiro.repositories.DespesaRepository;
import br.com.sop.controlefinanceiro.services.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService{

	@Autowired
	private DespesaRepository despesaRepository;

	@Override
	public List<Despesa> findAll() {
		var despesa = despesaRepository.findAll();
		return despesa;
	}

	@Override
	public Despesa create(Despesa despesa) {
		return despesaRepository.save(despesa);
	}
	
	
}