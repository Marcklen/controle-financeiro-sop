package br.com.sop.controllers.docs;


import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import br.com.sop.entities.dtos.out.EmpenhoDTO;
import br.com.sop.entities.dtos.out.PageDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    ResponseEntity<EmpenhoDTO> criarEmpenho(@RequestParam Integer idDespesa,
                                            @Valid @RequestBody EmpenhoCreateDTO empenhoCreateDTO) throws RegraDeNegocioException;

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

    @GetMapping("/paginacao-e-filtro-data")
    ResponseEntity<PageDTO<EmpenhoDTO>> listarEmpenhosComPaginacaoEFiltroDeDatas(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

    @Operation(summary = "Exclui Empenho", description = "Exclui um Empenho")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Exclui um empenho"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluirEmpenho(@PathVariable Integer id) throws RegraDeNegocioException;
}
