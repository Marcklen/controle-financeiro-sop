package br.com.sop.controllers;

import br.com.sop.controllers.docs.IDespesaDoc;
import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.dtos.out.DespesaDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.services.DespesaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@Tag(name = "DESPESA", description = "Responsável pelo gerenciamento das Despesas")
@RequestMapping("/api/v1/despesas")
@Slf4j
public class DespesaController implements IDespesaDoc {

    private final DespesaService despesaService;

    @Override
    public ResponseEntity<DespesaDTO> adicionarDespesa(DespesaCreateDTO despesaCreateDTO)
            throws RegraDeNegocioException {
        return new ResponseEntity<>(despesaService.criarDespesa(despesaCreateDTO), CREATED);
    }
}
