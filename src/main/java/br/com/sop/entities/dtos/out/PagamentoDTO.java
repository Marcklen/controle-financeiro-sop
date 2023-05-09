package br.com.sop.entities.dtos.out;

import br.com.sop.entities.dtos.in.PagamentoCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagamentoDTO extends PagamentoCreateDTO {

    @Schema(description = "ID do pagamento", example = "1")
    private Integer id_pagamento;

    @Schema(description = "ID do empenho vinculado ao pagamento", example = "1")
    private Integer id_empenho;

}