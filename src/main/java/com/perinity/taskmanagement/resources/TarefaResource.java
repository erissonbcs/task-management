package com.perinity.taskmanagement.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perinity.taskmanagement.dto.TarefaDTO;
import com.perinity.taskmanagement.entities.Tarefa;
import com.perinity.taskmanagement.services.TarefaService;
import com.perinity.taskmanagement.utils.GenericReturn;

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
	
	@PutMapping(value = "/alocar/{id}/idpessoa/{idPessoa}")
	public ResponseEntity<GenericReturn> alocarPessoa(@PathVariable Long id, @PathVariable Long idPessoa) {
		GenericReturn obj = tarefaService.alocar(id, idPessoa);
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value = "/finalizar/{id}")
	public ResponseEntity<GenericReturn> alocarPessoa(@PathVariable Long id) {
		GenericReturn obj = tarefaService.finalizar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/pendentes")
	public ResponseEntity<List<Tarefa>> listarTarefasPendentes() {
		List<Tarefa> lista = tarefaService.listarTarefasPendentes();
		return ResponseEntity.ok().body(lista);
	}
	
	
}
