package br.com.sop.entities.dtos.out;

import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DespesaDTO extends DespesaCreateDTO {

    @Schema(description = "ID da Despesa", example = "1")
    private Integer id_despesa;
}
