package com.perinity.taskmanagement.resources;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.taskmanagement.dto.TarefaDTO;
import com.perinity.taskmanagement.entities.Tarefa;
import com.perinity.taskmanagement.services.TarefaService;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaResource {
	
	TarefaService tarefaService;

	public TarefaResource(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}
	
	@PostMapping
	public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO) {
		Tarefa obj = tarefaService.salvar(tarefaDTO);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/alocar/{id}")
	public ResponseEntity<Tarefa> alocarPessoa(@PathVariable Long id) {
		Tarefa obj = tarefaService.alocar(id);
		return ResponseEntity.ok().body(obj);
	}
}
