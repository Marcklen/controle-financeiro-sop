package br.com.sop.controllers;

import br.com.sop.controllers.docs.IEmpenhoDoc;
import br.com.sop.entities.dtos.in.EmpenhoCreateDTO;
import br.com.sop.entities.dtos.out.EmpenhoDTO;
import br.com.sop.entities.dtos.out.PageDTO;
import br.com.sop.exceptions.RegraDeNegocioException;
import br.com.sop.services.EmpenhoService;
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


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/empenhos")
@RestController
@Validated
public class EmpenhoController implements IEmpenhoDoc {

    private final EmpenhoService empenhoService;

    @Override
    public ResponseEntity<EmpenhoDTO> criarEmpenho(Integer idDespesa, EmpenhoCreateDTO empenhoCreateDTO) throws RegraDeNegocioException {
        return new ResponseEntity<>(empenhoService.criarEmpenho(idDespesa, empenhoCreateDTO), CREATED);
    }

    @Override
    public ResponseEntity<List<EmpenhoDTO>> listarEmpenhos() {
        return new ResponseEntity<>(empenhoService.listarEmpenhos(), OK);
    }

    @Override
    public ResponseEntity<PageDTO<EmpenhoDTO>> listarEmpenhosComPaginacaoEFiltroDeDatas(Integer pagina, Integer tamanho, LocalDate dataInicio, LocalDate dataFim) {
        return new ResponseEntity<>(empenhoService.listaPaginadaFiltradaPorDataInicioEDataFim(pagina, tamanho, dataInicio, dataFim), OK);
    }
}
