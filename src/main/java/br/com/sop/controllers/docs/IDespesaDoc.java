package br.com.sop.controllers.docs;

import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.dtos.out.DespesaDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


@Tag(name = "DESPESAS", description = "Responsável pelo gerenciamento das Despesas")
public interface IDespesaDoc {

    @Operation(summary = "Adiciona Despesa", description = "Adicionar uma Despesa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna os dados da despesa adicionada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<DespesaDTO> adicionarDespesa(@Valid @RequestBody DespesaCreateDTO despesaCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Lista Despesa", description = "Lista de Despesas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de despesas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<DespesaDTO>> listarDespesas();
}
