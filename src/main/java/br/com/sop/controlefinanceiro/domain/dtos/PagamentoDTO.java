package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

//import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoDTO {

	private String numeroPagamento;

	private Integer anoPagamento;

	private LocalDate dataPagamento = LocalDate.now();

	private BigDecimal valorPagamento;

	private String observacao;

}
