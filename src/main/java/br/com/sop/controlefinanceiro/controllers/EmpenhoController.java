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

import br.com.sop.controlefinanceiro.domain.Empenho;
import br.com.sop.controlefinanceiro.services.EmpenhoService;

@RestController
@RequestMapping("/api/empenhos")
public class EmpenhoController {

	@Autowired
	private EmpenhoService empenhoService;
	
	@GetMapping
	public List<Empenho> findAll() {
		return empenhoService.findAll();
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Empenho create (@RequestBody Empenho emp) {
		return empenhoService.create(emp);
	}
}