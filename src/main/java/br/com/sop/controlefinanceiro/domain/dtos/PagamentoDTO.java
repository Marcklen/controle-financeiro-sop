package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.sop.controlefinanceiro.domain.Empenho;

//import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagamentoDTO {

	private Integer pagamentoId;

	private String numeroPagamento;

	private Integer anoPagamento;

	private LocalDate dataPagamento = LocalDate.now();

	private BigDecimal valorPagamento;

	private String observacao;
	
	private Empenho empenho;

}
