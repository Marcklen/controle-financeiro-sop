package br.com.sop.controllers.docs;


import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import br.com.sop.entities.dtos.out.EmpenhoDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "EMPENHOS", description = "Responsável pelo gerenciamento dos Empenhos")
public interface IEmpenhoDoc {

    @Operation(summary = "Adiciona Empenho", description = "Adicionar um Empenho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna os dados do empenho adicionado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<EmpenhoDTO> criarEmpenho(@RequestParam Integer idDespesa, @Valid @RequestBody EmpenhoCreateDTO empenhoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Lista Empenho", description = "Lista de Empenhos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de empenhos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<EmpenhoDTO>> listarEmpenhos();
}
