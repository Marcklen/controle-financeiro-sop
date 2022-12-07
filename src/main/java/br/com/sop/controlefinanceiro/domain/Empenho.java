package br.com.sop.controlefinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empenho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numeroEmpenho;
	@Column(unique = true)
	private Integer anoEmpenho;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEmpenho = LocalDate.now();
	@Column(precision = 20, scale = 2)
	private BigDecimal valorEmpenho;
	private String observacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "despesa_id", referencedColumnName = "numeroProtocolo", nullable = false)
	private Despesa despesa;

	@OneToMany(mappedBy = "empenho")
	private Set<Pagamento> pagamento;
}