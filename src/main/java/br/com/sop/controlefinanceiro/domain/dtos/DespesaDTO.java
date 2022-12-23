package br.com.sop.controlefinanceiro.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sop.controlefinanceiro.domain.Empenho;
import br.com.sop.controlefinanceiro.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DespesaDTO {

	private Integer despesaId;

    private String numeroProtocolo;

    private String tipoDespesa;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataProtocolo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    private String credorDespesa;

    private String descricaoDespesa;

    private BigDecimal valorDespesa;

    //private String status;
    private Status status;

    private Set<Empenho> empenhos;

}
