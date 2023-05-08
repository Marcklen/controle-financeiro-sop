package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class DespesaCreateDTO {

    @Size(min = 1, max = 9999)
    @Schema(description = "Número do Protocolo", example = "2023")
    private Integer numero_protocolo;

//    @NotNull
//    @Schema(description = "Tipo da Despesa", example = "AGUARDANDO_EMPENHO", enumAsRef = true)
//    private StatusDespesa tipo_despesa;

    @NotNull
    @FutureOrPresent(message = "O campo data protocolo deve ser atual ou futuro!")
    private LocalDate data_protocolo;

    @NotNull
    @FutureOrPresent(message = "O campo data vencimento deve ser atual ou futuro!")
    private LocalDate data_vencimento;

    @NotNull
    @Schema(description = "Credor da Despesa", example = "Empresa Cearense de Recursos Hídricos - Cogerh")
    private String credor_despesa;

    @NotNull
    @Schema(description = "Descrição da Despesa", example = "Pagamento de Serviços de Terceiros - Pessoa Jurídica")
    private String descricao_despesa;


    @Schema(description = "Valor da Despesa", example = "1000.00")
    private Double valor_despesa;

}