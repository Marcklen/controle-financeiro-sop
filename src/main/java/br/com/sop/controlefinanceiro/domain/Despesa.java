package br.com.sop.controlefinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sop.controlefinanceiro.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DESPESA 
//-> Que agrega informações referente aos processos financeiros da instituição.

@Entity(name = "tb_despesas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer despesaId;

    @Column(name="numero_protocolo", nullable = false, unique = true)
    private String numeroProtocolo;

    @Column(name="tipo_despesa")
    private String tipoDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="data_protocolo")
    private LocalDate dataProtocolo = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @Column(name="credor_despesa", nullable = false)
    private String credorDespesa;

    @Column(name="descricao_despesa", nullable = false)
    private String descricaoDespesa;

    @Column(name="valor_despesa", precision = 20, scale = 2)
    private BigDecimal valorDespesa;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;

    // TODO: Pode ser nulo, verificar
    @OneToMany(mappedBy = "despesa")
    private Set<Empenho> empenhos;
}