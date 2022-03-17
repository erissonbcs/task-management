package com.perinity.taskmanagement.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.taskmanagement.dto.PessoaDTO;
import com.perinity.taskmanagement.entities.Pessoa;
import com.perinity.taskmanagement.reports.RelatorioHorasGasta;
import com.perinity.taskmanagement.reports.RelatorioMediaHorasGasta;
import com.perinity.taskmanagement.services.PessoaService;
import com.perinity.taskmanagement.utils.GenericReturn;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	
	PessoaService pessoaService;

	public PessoaResource(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
		Pessoa obj = pessoaService.salvar(pessoaDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> alterarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO){
		Pessoa obj = pessoaService.atualizar(id, pessoaDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericReturn> deletarPessoa(@PathVariable Long id){
		GenericReturn obj = pessoaService.deletar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<RelatorioHorasGasta>> listarPessoas(){
		List<RelatorioHorasGasta> relatorio = pessoaService.listar();
		return ResponseEntity.ok().body(relatorio);
	}
	
	@GetMapping(value = "/gastos")
	public ResponseEntity<List<RelatorioMediaHorasGasta>> buscarPessoas(@RequestBody RelatorioMediaHorasGasta relatorioMediaHorasGasta){
		List<RelatorioMediaHorasGasta> relatorio = pessoaService.buscar(relatorioMediaHorasGasta);
		return ResponseEntity.ok().body(relatorio);
	}
	
}
