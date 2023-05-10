package br.com.sop.entities.dtos.in;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmpenhoCreateDTO {

    @NotNull(message = "O campo ano empenho é obrigatório!")
    @Schema(description = "Ano do empenho", example = "2023")
    private Integer ano_empenho;

    @NotNull(message = "O campo número empenho é obrigatório e unico!")
    @Schema(description = "Número do empenho", example = "123456")
    private Integer numero_empenho;

    @FutureOrPresent(message = "O campo data empenho deve ser atual ou futuro!")
    @Schema(description = "Data do empenho", example = "2023-05-09")
    private LocalDate data_empenho;

    @Min(value = 1, message = "O campo valor empenho deve ser maior que zero!")
    @Schema(description = "Valor do empenho", example = "1000.00")
    private Double valor_empenho;

    @NotBlank(message = "O campo observação empenho é obrigatório!")
    @Schema(description = "Observação do empenho", example = "Observação do empenho")
    private String observacao_empenho;

}