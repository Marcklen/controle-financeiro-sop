package br.com.sop.repositories;

import br.com.sop.entities.DespesaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Integer> {

    // Consultas Relação de Despesa usando filtros de: (Data protocolo, Tipo Despesa, Credor )
    // @Query(value = "SELECT * FROM DESPESA WHERE DATA_PROTOCOLO = ?1 AND TIPO_DESPESA = ?2 AND CREDOR_DESPESA = ?3", nativeQuery = true)

}
