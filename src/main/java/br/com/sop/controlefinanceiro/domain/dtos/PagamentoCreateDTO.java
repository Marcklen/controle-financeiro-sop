package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoCreateDTO {

	private String numeroPagamento;

	private Integer anoPagamento;

	private LocalDate dataPagamento = LocalDate.now();

	private BigDecimal valorPagamento;

	private String observacao;
}
