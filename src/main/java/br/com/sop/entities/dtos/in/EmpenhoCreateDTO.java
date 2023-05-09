package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpenhoCreateDTO {

    @Schema(description = "Ano do empenho", example = "2023")
    private Integer ano_empenho;

    @Schema(description = "Número do empenho", example = "123456")
    private Integer numero_empenho;

    @Schema(description = "Data do empenho", example = "2023-05-09")
    private LocalDate data_empenho;

    @Schema(description = "Valor do empenho", example = "1000.00")
    private Double valor_empenho;

    @Schema(description = "Observação do empenho", example = "Observação do empenho")
    private String observacao_empenho;

}