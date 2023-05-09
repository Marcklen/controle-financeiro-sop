package br.com.sop.entities.dtos.out;

import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmpenhoDTO extends EmpenhoCreateDTO {

    @Schema(description = "Id do empenho", example = "1")
    private Integer id_empenho;
}