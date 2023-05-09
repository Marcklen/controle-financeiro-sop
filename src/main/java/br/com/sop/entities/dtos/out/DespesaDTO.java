package br.com.sop.entities.dtos.out;

import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.enums.StatusDespesa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DespesaDTO extends DespesaCreateDTO {

    @Schema(description = "Status referente a Despesa", example = "PAGO")
    @Enumerated(EnumType.STRING)
    private StatusDespesa tipo_despesa;

    @Schema(description = "ID da Despesa", example = "1")
    private Integer id_despesa;
}
