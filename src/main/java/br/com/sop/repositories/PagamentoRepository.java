package br.com.sop.repositories;

import br.com.sop.entities.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {

    // 4. Consultas Relação de Pagamento usando filtros de: (Periodo de Data de Pagamento)
    @Query("SELECT p FROM PAGAMENTO p WHERE p.data_pagamento BETWEEN ?1 AND ?2")
    List<PagamentoEntity> findByData_pagamentoBetween(LocalDate dataInicio, LocalDate dataFim);
}
