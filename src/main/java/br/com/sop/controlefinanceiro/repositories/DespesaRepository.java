package br.com.sop.controlefinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sop.controlefinanceiro.domain.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer>{

}
