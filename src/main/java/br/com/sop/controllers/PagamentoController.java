package br.com.sop.controllers;

import br.com.sop.controllers.docs.IPagamentoDoc;
import br.com.sop.entities.dtos.in.PagamentoCreateDTO;
import br.com.sop.entities.dtos.out.PagamentoDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/pagamentos")
@RestController
@Validated
public class PagamentoController implements IPagamentoDoc {

    private final PagamentoService pagamentoService;

    @Override
    public ResponseEntity<PagamentoDTO> criarPagamento(Integer idEmpenho, PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(pagamentoService.criarPagamento(idEmpenho, pagamentoCreateDTO), CREATED);
    }

    @Override
    public ResponseEntity<List<PagamentoDTO>> listarPagamentos() {
        return new ResponseEntity<>(pagamentoService.listarPagamentos(), OK);
    }
}
