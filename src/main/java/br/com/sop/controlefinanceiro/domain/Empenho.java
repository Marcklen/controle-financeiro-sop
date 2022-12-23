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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_empenhos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Empenho implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer empenhoId;

	@Column(name = "numero_empenho", unique = true, nullable = false)
	private String numeroEmpenho;

	@Column(name = "ano_empenho")
	private Integer anoEmpenho;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_empenho")
	private LocalDate dataEmpenho = LocalDate.now();

	@Column(name = "valor_empenho", precision = 20, scale = 2)
	private BigDecimal valorEmpenho;

	private String observacao;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "despesa_id", referencedColumnName = "despesaId", nullable = false)
	private Despesa despesa;

	// TODO: Pode ser nulo, verificar
	@OneToMany(mappedBy = "empenho")
	private Set<Pagamento> pagamentos;
}