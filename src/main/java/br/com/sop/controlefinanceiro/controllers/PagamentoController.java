package br.com.sop.controlefinanceiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.sop.controlefinanceiro.domain.Pagamento;
import br.com.sop.controlefinanceiro.services.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping
	public List<Pagamento> findAll() {
		return pagamentoService.findAll();
	}

	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Pagamento create (@RequestBody Pagamento pgto) {
		return pagamentoService.create(pgto);
	}
}
