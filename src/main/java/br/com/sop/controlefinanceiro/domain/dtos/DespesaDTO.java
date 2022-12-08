package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DespesaDTO {

	private Integer numeroProtocolo;
	private String tipoDespesa;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataProtocolo = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;
	private String credorDespesa;
	private String descricaoDespesa;
	private BigDecimal valorDespesa;
}
