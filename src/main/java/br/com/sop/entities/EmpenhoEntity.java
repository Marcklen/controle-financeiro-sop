package br.com.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "EMPENHO")
@Getter
@Setter
public class EmpenhoEntity {
    /**
     * - Ano do Empenho
     * - Número do Empenho
     * - Data do Empenho
     * - Valor do Empenho
     * - Observação
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPENHO_SEQ")
    @SequenceGenerator(name = "EMPENHO_SEQ", sequenceName = "EMPENHO_SEQ", allocationSize = 1)
    private Integer id_empenho;

    @Column(name = "ANO_EMPENHO")
    private Integer ano_empenho;

    @Column(name = "NUMERO_EMPENHO", unique = true)
    private Integer numero_empenho;

    @Column(name = "DATA_EMPENHO")
    private LocalDate data_empenho;

    @Column(name = "VALOR_EMPENHO", scale = 10, precision = 2)
    private Double valor_empenho;

    @Column(name = "OBSERVACAO_EMPENHO")
    private String observacao_empenho;

    @Column(name = "ID_DESPESA")
    private Integer id_despesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DESPESA", referencedColumnName = "ID_DESPESA", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonSerialize(as = DespesaEntity.class)
    private DespesaEntity despesa;

    @OneToMany(mappedBy = "empenho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PagamentoEntity> pagamentos;
}
