package br.com.sop.repositories;

import br.com.sop.entities.DespesaEntity;
import br.com.sop.entities.enums.StatusDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaEntity, Integer> {

    // Consultas Relação de Despesa usando filtros de: (Data protocolo, Tipo Despesa, Credor )

    // consultar por data do protocolo
//    List<DespesaEntity> findByData_protocoloBetween(LocalDate dataInicio, LocalDate dataFim);
//
//    // consultar por tipo de despesa
//    List<DespesaEntity> findByTipo_despesaExistsAndTipo_despesaEqualsIgnoreCase(StatusDespesa tipo_despesa);
//
//    // consultar por credor
//    List<DespesaEntity> findByCredor_despesaEqualsIgnoreCase(String credor_despesa);

}