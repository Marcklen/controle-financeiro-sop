package br.com.sop.controlefinanceiro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import br.com.sop.controlefinanceiro.domain.Despesa;
import br.com.sop.controlefinanceiro.services.DespesaService;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

	@Autowired
	private DespesaService despesaService;

	@GetMapping
	public List<Despesa>findAll () {
		return despesaService.findAll();
//		return ResponseEntity.ok(despesaService.findAll(desp)).build();
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Despesa create(@RequestBody Despesa desp) {
		return despesaService.create(desp);
	}
}