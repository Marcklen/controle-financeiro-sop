package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class PagamentoCreateDTO {

    @NotNull(message = "O campo ano pagamento é obrigatório!")
    @Schema(description = "Ano do pagamento", example = "2023")
    private Integer ano_pagamento;

    @NotNull(message = "O campo número pagamento é obrigatório e unico!")
    @Schema(description = "Número do pagamento", example = "654321", required = true, format = "int32")
    private Integer numero_pagamento;

    @FutureOrPresent(message = "O campo data pagamento deve ser atual ou futuro!")
    @Schema(description = "Data do pagamento", example = "2023-05-09")
    private LocalDate data_pagamento;

    @Min(value = 1, message = "O campo valor pagamento deve ser maior que zero!")
    @Schema(description = "Valor do pagamento", example = "1000.00")
    private Double valor_pagamento;

    @NotBlank(message = "O campo observação pagamento é obrigatório!")
    @Schema(description = "Observação do pagamento", example = "Observação do pagamento")
    private String observacao_pagamento;
}
