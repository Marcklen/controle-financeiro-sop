package br.com.sop.controllers;

import br.com.sop.controllers.docs.IDespesaDoc;
import br.com.sop.entities.dtos.in.DespesaCreateDTO;
import br.com.sop.entities.dtos.out.DespesaDTO;
import br.com.sop.entities.enums.StatusDespesa;
import br.com.sop.services.DespesaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/despesas")
@Slf4j
@Validated
public class DespesaController implements IDespesaDoc {

    private final DespesaService despesaService;

    @Override
    public ResponseEntity<DespesaDTO> adicionarDespesa(DespesaCreateDTO despesaCreateDTO) {
        return new ResponseEntity<>(despesaService.criarDespesa(despesaCreateDTO), CREATED);
    }

    @Override
    public ResponseEntity<List<DespesaDTO>> listarDespesas() {
        return new ResponseEntity<>(despesaService.listarDespesas(), OK);
    }

    @Override
    public ResponseEntity<List<DespesaDTO>> listarDespesasPorDataProtocolo(LocalDate dataInicio, LocalDate dataFim) {
        return new ResponseEntity<>(despesaService.listarDespesasPorDataProtocolo(dataInicio, dataFim), OK);
    }

    @Override
    public ResponseEntity<List<DespesaDTO>> listarPagamentoComFiltroStatus(StatusDespesa statusDespesa) {
        return new ResponseEntity<>(despesaService.listarDespesaPorStatus(statusDespesa), OK);
    }

    @Override
    public ResponseEntity<List<DespesaDTO>> listarPagamentoComFiltroCredor(String credor) {
        return new ResponseEntity<>(despesaService.listarDespesaPorCredor(credor), OK);
    }
}
