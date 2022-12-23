package br.com.sop.controlefinanceiro.controllers;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sop.controlefinanceiro.domain.Despesa;
import br.com.sop.controlefinanceiro.domain.Empenho;
import br.com.sop.controlefinanceiro.domain.Pagamento;
import br.com.sop.controlefinanceiro.domain.dtos.DespesaDTO;
import br.com.sop.controlefinanceiro.domain.dtos.EmpenhoCreateDTO;
import br.com.sop.controlefinanceiro.domain.dtos.EmpenhoDTO;
import br.com.sop.controlefinanceiro.domain.dtos.PagamentoDTO;
import br.com.sop.controlefinanceiro.services.DespesaService;
import br.com.sop.controlefinanceiro.services.EmpenhoService;
import br.com.sop.controlefinanceiro.services.PagamentoService;

@RestController
@RequestMapping("api/v1/despesas")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@Autowired
	private EmpenhoService empenhoService;

	@Autowired
	private PagamentoService pagamentoService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public ResponseEntity<?> getAll() {
		try {
			var despesaList = despesaService.getAll();
			var result = despesaList.stream().map(despesa -> modelMapper.map(despesa, DespesaDTO.class)).toList();
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Despesa despesa) {
		try {
			despesa = despesaService.create(despesa);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(despesa.getDespesaId()).toUri();
			return ResponseEntity.created(uri).body(despesa);
		} catch (RuntimeException e) {
			// TODO: handle exception
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("{despesaId}/empenhos")
	public ResponseEntity<?> createEmpenho(@PathVariable Integer despesaId,
			@RequestBody EmpenhoCreateDTO empenhoToCreate) {
		try {
			var optDespesa = despesaService.getById(despesaId);
			if (!optDespesa.isPresent())
				return ResponseEntity.badRequest().body("Despesa inválida");

			var empenho = modelMapper.map(empenhoToCreate, Empenho.class);
			empenho.setDespesa(optDespesa.get());

			empenho = empenhoService.create(empenho);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(empenho.getEmpenhoId()).toUri();

			return ResponseEntity.created(uri).body(empenho);

		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// buscar todos os empenhos
	@GetMapping("{despesaId}/empenhos")
	public ResponseEntity<?> getAllEmp() {
		try {
			var empenhoList = empenhoService.getAll();
			var result = empenhoList.stream().map(empenho -> modelMapper.map(empenho, EmpenhoDTO.class)).toList();
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("{despesaId}/empenhos/{empenhoId}")
	public ResponseEntity<?> getEmpenhoInDespesa(@PathVariable Integer empenhoId) {
		try {
			var optEmpenho = empenhoService.getById(empenhoId);
			if (!optEmpenho.isPresent())
				return ResponseEntity.notFound().build();

			return ResponseEntity.ok(optEmpenho.get());
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("{despesaId}/empenhos/{empenhoId}")
	public ResponseEntity<?> deleteEmpenhoInDespesa(@PathVariable Integer empenhoId) {
		try {
			var optEmpenho = empenhoService.getById(empenhoId);
			if (!optEmpenho.isPresent())
				return ResponseEntity.badRequest().body("Empenho inválido");

			empenhoService.deleteById(empenhoId);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping("{despesaId}/empenhos/{empenhoId}/pagamentos")
	public ResponseEntity<?> createPagamentoInEmpenho(@PathVariable Integer despesaId, @PathVariable Integer empenhoId,
			@RequestBody PagamentoDTO pagamentoToCreate) {
		try {
			var optEmpenho = empenhoService.getById(empenhoId);
			if (!optEmpenho.isPresent())
				return ResponseEntity.badRequest().body("Empenho inválido");

			if (optEmpenho.get().getDespesa().getDespesaId() != despesaId)
				return ResponseEntity.badRequest().body("Despesa inválida");

			var pagamento = modelMapper.map(pagamentoToCreate, Pagamento.class);
			pagamento = pagamentoService.create(pagamento);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(pagamento.getPagamentoId()).toUri();

			return ResponseEntity.created(uri).body(pagamento);
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	// buscar todos os pagamentos
	@GetMapping("{despesaId}/empenhos/{empenhoId}/pagamentos")
	public ResponseEntity<?> getAllPgto() {
		try {
			var pagamentoList = pagamentoService.getAll();
			var result = pagamentoList.stream().map(pagamento -> modelMapper.map(pagamento, PagamentoDTO.class)).toList();
			return ResponseEntity.ok(result);
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("{despesaId}/empenhos/{empenhoId}/pagamentos/{pagamentoId}")
	public ResponseEntity<?> getPagamentoInEmpenho(@PathVariable Integer despesaId, @PathVariable Integer empenhoId,
			@PathVariable Integer pagamentoId) {
		try {
			var optEmpenho = empenhoService.getById(empenhoId);
			if (!optEmpenho.isPresent())
				return ResponseEntity.notFound().build();

			if (optEmpenho.get().getDespesa().getDespesaId() != despesaId)
				return ResponseEntity.notFound().build();

			var optPagamento = pagamentoService.getById(pagamentoId);
			if (!optPagamento.isPresent())
				return ResponseEntity.notFound().build();

			return ResponseEntity.ok(optPagamento.get());
		} catch (RuntimeException e) {
			return ResponseEntity.internalServerError().build();
		}
	}
}