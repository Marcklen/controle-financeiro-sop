package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpenhoCreateDTO {

	private String numeroEmpenho;

    private Integer anoEmpenho;

    private LocalDate dataEmpenho;

    private BigDecimal valorEmpenho;

    private String observacao;
}
