package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagamentoCreateDTO {

    @Schema(description = "Ano do pagamento", example = "2023")
    private Integer ano_pagamento;

    @Schema(description = "Número do pagamento", example = "654321")
    private Integer numero_pagamento;

    @Schema(description = "Data do pagamento", example = "2023-05-09")
    private LocalDate data_pagamento;

    @Schema(description = "Valor do pagamento", example = "1000.00")
    private Double valor_pagamento;

    @Schema(description = "Observação do pagamento", example = "Observação do pagamento")
    private String observacao_pagamento;
}
