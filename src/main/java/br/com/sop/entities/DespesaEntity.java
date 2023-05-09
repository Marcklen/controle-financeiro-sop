package br.com.sop.entities;

import br.com.sop.entities.enums.StatusDespesa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "DESPESA")
@Getter
@Setter
public class DespesaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESPESA_SEQ")
    @SequenceGenerator(name = "DESPESA_SEQ", sequenceName = "DESPESA_SEQ", allocationSize = 1)
    private Integer id_despesa;

    @Column(name = "NUMERO_PROTOCOLO", unique = true)
    private Integer numero_protocolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_DESPESA")
    private StatusDespesa tipo_despesa;

    @Column(name = "DATA_PROTOCOLO")
    private LocalDate data_protocolo;

    @Column(name = "DATA_VENCIMENTO")
    private LocalDate data_vencimento;

    @Column(name = "CREDOR_DESPESA")
    private String credor_despesa;

    @Column(name = "DESCRICAO_DESPESA")
    private String descricao_despesa;

    @Column(name = "VALOR_DESPESA", scale = 10, precision = 2)
    private Double valor_despesa;

    // pode ter nenhum ou muitos empenhos
    @JsonIgnore
    @OneToMany(mappedBy = "despesa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<EmpenhoEntity> empenhos;
}