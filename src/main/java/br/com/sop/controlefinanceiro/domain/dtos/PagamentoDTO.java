package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sop.controlefinanceiro.domain.Empenho;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PagamentoDTO {

	private Integer numeroPagamento;
	private Integer anoPagamento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamento = LocalDate.now();
	private BigDecimal valorPagamento;
	private String observacao;
	private Empenho empenho;

}
