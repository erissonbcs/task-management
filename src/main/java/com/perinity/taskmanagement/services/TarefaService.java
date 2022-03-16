package com.perinity.taskmanagement.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.dto.TarefaDTO;
import com.perinity.taskmanagement.entities.Departamento;
import com.perinity.taskmanagement.entities.Pessoa;
import com.perinity.taskmanagement.entities.Tarefa;
import com.perinity.taskmanagement.repositories.DepartamentoRepository;
import com.perinity.taskmanagement.repositories.PessoaRepository;
import com.perinity.taskmanagement.repositories.TarefaRepository;
import com.perinity.taskmanagement.services.exceptions.EntityNotFoundException;

@Service
public class TarefaService {

	PessoaRepository pessoaRepository;
	DepartamentoRepository departamentoRepository;
	TarefaRepository tarefaRepository;

	public TarefaService(PessoaRepository pessoaRepository, 
			DepartamentoRepository departamentoRepository, TarefaRepository tarefaRepository) {
		this.pessoaRepository = pessoaRepository;
		this.departamentoRepository = departamentoRepository;
		this.tarefaRepository = tarefaRepository;
	}

	public Tarefa salvar(TarefaDTO tarefaDTO) {
		Departamento departamento = buscarDepartamentoPorId(tarefaDTO.getIdDepartamento());
		Tarefa tarefa = new Tarefa();
		tarefa.setDepartamento(departamento);
		tarefa.setDescricao(tarefaDTO.getDescricao());
		tarefa.setDuracao(tarefaDTO.getDuracao());
		tarefa.setFinalizada(tarefaDTO.isFinalizada());
		tarefa.setPrazo(tarefaDTO.getPrazo());
		tarefa.setTitulo(tarefaDTO.getTitulo());
		
		return tarefaRepository.save(tarefa);
	}
	
	public Tarefa alocar(Long id) {
		Tarefa tarefa = buscarTarefaPorId(id);
		return tarefa;
	}

	private Departamento buscarDepartamentoPorId(Long id) {
		Optional<Departamento> obj = departamentoRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Departamento informado não foi encontrado."));
	}

	private Pessoa buscarPessoaPorId(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Pessoa informada não foi encontrada."));
	}
	
	private Tarefa buscarTarefaPorId(Long id) {
		Optional<Tarefa> obj = tarefaRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Tarefa informada não foi encontrada."));
	}
	
	
}
