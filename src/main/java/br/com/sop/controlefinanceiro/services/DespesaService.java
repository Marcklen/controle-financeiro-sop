package br.com.sop.controlefinanceiro.services;

import org.springframework.stereotype.Service;

import br.com.sop.controlefinanceiro.domain.Despesa;
import br.com.sop.controlefinanceiro.repositories.DespesaRepository;
import br.com.sop.controlefinanceiro.services.impl.ServiceMaster;

@Service
public class DespesaService extends ServiceMaster<Despesa, DespesaRepository> {

}
