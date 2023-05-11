package br.com.sop.repositories;

import br.com.sop.entities.EmpenhoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmpenhoRepository extends JpaRepository<EmpenhoEntity, Integer> {

    // 3. Consultas Relação de Empenho usando filtros de: (Periodo de Data de empenho)
    @Query("SELECT e FROM EMPENHO e WHERE e.data_empenho BETWEEN ?1 AND ?2")
    Page<EmpenhoEntity> findByData_empenhoBetween(Pageable pageable, LocalDate dataInicio, LocalDate dataFim);

}