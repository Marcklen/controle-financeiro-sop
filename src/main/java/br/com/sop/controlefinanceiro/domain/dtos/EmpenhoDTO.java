package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sop.controlefinanceiro.domain.Pagamento;
import lombok.Data;


// Listagem De Empenhos
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmpenhoDTO {

	private Integer empenhoId;

    private String numeroEmpenho;

    private Integer anoEmpenho;

    private LocalDate dataEmpenho;

    private BigDecimal valorEmpenho;

    private String observacao;

    private Integer despesaDespesaId;

	private Set<Pagamento> pagamentos;

}
