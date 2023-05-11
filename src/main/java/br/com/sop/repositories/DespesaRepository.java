package br.com.sop.repositories;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.enums.StatusDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Integer> {

    //2. Consultas Relação de Despesa usando filtros de: (Data protocolo, Tipo Despesa, Credor )
    @Query("SELECT d FROM DESPESA d WHERE d.data_protocolo BETWEEN ?1 AND ?2")
    List<DespesaEntity> findByData_protocoloBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT d FROM DESPESA d WHERE d.tipo_despesa = ?1")
    List<DespesaEntity> findByTipo_despesaExists(StatusDespesa tipo_despesa);

    @Query("SELECT DISTINCT d FROM DESPESA d WHERE d.credor_despesa LIKE %:credor_despesa%")
    List<DespesaEntity> findDistinctByCredor_despesaContainingIgnoreCase(String credor_despesa);

    /**
     * SELECT DISTINCT
     * 	d
     * FROM
     * 	DESPESA d
     * WHERE
     * 	d.credor_despesa LIKE '%' || 'Spider Man - ME' || '%';
     */
}