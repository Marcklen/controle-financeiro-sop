package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class DespesaCreateDTO {

    @NotNull(message = "O campo ano protocolo é obrigatório!")
    @Schema(description = "Número do Protocolo", example = "2023", required = true, format = "123456")
    private Integer numero_protocolo;

    @NotNull
    @FutureOrPresent(message = "O campo data do protocolo deve ser atual ou futuro!")
    private LocalDate data_protocolo;

    @NotNull
    @FutureOrPresent(message = "O campo data do vencimento deve ser atual ou futuro!")
    private LocalDate data_vencimento;

    @NotNull(message = "O campo credor da despesa é obrigatório!")
    @Schema(description = "Credor da Despesa", example = "Empresa Cearense de Recursos Hídricos - Cogerh")
    private String credor_despesa;

    @NotNull(message = "O campo descrição da despesa é obrigatório!")
    @Schema(description = "Descrição da Despesa", example = "Pagamento de Serviços de Terceiros - Pessoa Jurídica")
    private String descricao_despesa;

    @Min(value = 1, message = "O campo valor da despesa deve ser maior que zero!")
    @Schema(description = "Valor da Despesa", example = "1000.00")
    private Double valor_despesa;
}