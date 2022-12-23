package br.com.sop.controlefinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tb_pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pagamentoId;

	@Column(name = "numero_pagamento", unique = true, nullable = false)
	private String numeroPagamento;

	@Column(name = "ano_pagamento")
	private Integer anoPagamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento = LocalDate.now();

	@Column(precision = 20, scale = 2)
	private BigDecimal valorPagamento;

	private String observacao;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empenho_id", referencedColumnName = "empenhoId", nullable = false)
	private Empenho empenho;
}