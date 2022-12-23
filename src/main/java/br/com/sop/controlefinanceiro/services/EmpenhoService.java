package br.com.sop.controlefinanceiro.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sop.controlefinanceiro.domain.Empenho;
import br.com.sop.controlefinanceiro.repositories.EmpenhoRepository;
import br.com.sop.controlefinanceiro.services.impl.ServiceMaster;

@Service
public class EmpenhoService extends ServiceMaster<Empenho, EmpenhoRepository> {

    public List<Empenho> teste() {
        var repository = super.getRepository();
        return repository.findAll();
    }
}