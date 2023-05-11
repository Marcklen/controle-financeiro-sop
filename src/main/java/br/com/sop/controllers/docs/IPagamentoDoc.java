package br.com.sop.controllers.docs;

import br.com.sop.entities.dtos.in.PagamentoCreateDTO;
import br.com.sop.entities.dtos.out.PagamentoDTO;
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

@Tag(name = "PAGAMENTOS", description = "Responsável pelo gerenciamento dos Pagamentos")
public interface IPagamentoDoc {

    @Operation(summary = "Adiciona Pagamento", description = "Adicionar um Pagamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Retorna os dados do pagamento adicionado"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    ResponseEntity<PagamentoDTO> criarPagamento(@RequestParam Integer idEmpenho,
                                                @Valid @RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Lista Pagamento", description = "Lista de Pagamentos")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pagamentos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    ResponseEntity<List<PagamentoDTO>> listarPagamentos();

    @Operation(summary = "Lista Pagamento com Filtro", description = "Lista de Pagamentos filtrados por data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pagamentos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/filtro-data")
    ResponseEntity<List<PagamentoDTO>> listarPagamentoComFiltroDeDatas(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim);

    @Operation(summary = "Exclui Pagamento", description = "Exclui um Pagamento")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Exclui um pagamentos"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluirPagamento(@PathVariable Integer id) throws RegraDeNegocioException;
}
