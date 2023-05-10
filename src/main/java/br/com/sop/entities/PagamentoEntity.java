package br.com.sop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "PAGAMENTO")
@Getter
@Setter
public class PagamentoEntity {
    /**
     * - Ano do Pagamento
     * - Número do Pagamento
     * - Data do Pagamento
     * - Valor do Pagamento
     * - Observação
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGAMENTO_SEQ")
    @SequenceGenerator(name = "PAGAMENTO_SEQ", sequenceName = "PAGAMENTO_SEQ", allocationSize = 1)
    private Integer id_pagamento;

    @Column(name = "ANO_PAGAMENTO")
    private Integer ano_pagamento;

    @Column(name = "NUMERO_PAGAMENTO", unique = true)
    private Integer numero_pagamento;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDate data_pagamento;

    @Column(name = "VALOR_PAGAMENTO", scale = 10, precision = 2)
    private Double valor_pagamento;

    @Column(name = "OBSERVACAO_PAGAMENTO")
    private String observacao_pagamento;

    @Column(name = "ID_EMPENHO")
    private Integer id_empenho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPENHO", referencedColumnName = "ID_EMPENHO", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonSerialize(as = EmpenhoEntity.class)
    private EmpenhoEntity empenho;
}
