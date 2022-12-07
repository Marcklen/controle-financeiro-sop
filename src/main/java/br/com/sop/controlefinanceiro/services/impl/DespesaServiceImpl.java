package br.com.sop.controlefinanceiro.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.sop.controlefinanceiro.domain.Despesa;
import br.com.sop.controlefinanceiro.repositories.DespesaRepository;
import br.com.sop.controlefinanceiro.services.DespesaService;

@Service
public class DespesaServiceImpl implements DespesaService{

	@Autowired
	private DespesaRepository despesaRepository;

	@Override
	public List<Despesa> findAll(Despesa filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Despesa> example = Example.of(filtro, matcher);
		return despesaRepository.findAll(example);
	}
}
