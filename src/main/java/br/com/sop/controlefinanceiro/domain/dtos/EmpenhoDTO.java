package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sop.controlefinanceiro.domain.Despesa;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmpenhoDTO {

	private Integer numeroEmpenho;
	private Integer anoEmpenho;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataEmpenho = LocalDate.now();
	private BigDecimal valorEmpenho;
	private String observacao;
	private Despesa despesa;

}
