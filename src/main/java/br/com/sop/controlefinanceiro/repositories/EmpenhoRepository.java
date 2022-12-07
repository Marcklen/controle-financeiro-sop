package br.com.sop.controlefinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sop.controlefinanceiro.domain.Empenho;

@Repository
public interface EmpenhoRepository extends JpaRepository<Empenho, Integer>{

}
