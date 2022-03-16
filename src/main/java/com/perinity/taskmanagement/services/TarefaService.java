package com.perinity.taskmanagement.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.dto.TarefaDTO;
import com.perinity.taskmanagement.entities.Departamento;
import com.perinity.taskmanagement.entities.Pessoa;
import com.perinity.taskmanagement.entities.Tarefa;
import com.perinity.taskmanagement.repositories.DepartamentoRepository;
import com.perinity.taskmanagement.repositories.PessoaRepository;
import com.perinity.taskmanagement.repositories.TarefaRepository;
import com.perinity.taskmanagement.services.exceptions.EntityNotFoundException;
import com.perinity.taskmanagement.utils.GenericReturn;

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
	
	public GenericReturn alocar(Long id, Long idPessoa) {
		GenericReturn genericReturn;
		
		Tarefa tarefa = buscarTarefaPorId(id);
		if(tarefa.getPessoa() != null) {
			Pessoa pessoa = buscarPessoaPorId(tarefa.getPessoa().getId());
			String msg = "Esta tarefa já está alocada para "+pessoa.getNome();
			genericReturn = new GenericReturn(HttpStatus.OK.value(), msg, tarefa);
			return genericReturn;
		}
		
		Pessoa pessoa = buscarPessoaPorId(idPessoa);
		if(pessoa.getDepartamento().getId() != tarefa.getDepartamento().getId()) {
			String msg = "A tarefa só pode ser alocado para uma pessoa que tenha o mesmo departamento.";
			genericReturn = new GenericReturn(HttpStatus.OK.value(), msg, tarefa);
			return genericReturn;
		}
		
		tarefa.setPessoa(pessoa);
		
		tarefaRepository.save(tarefa);
		String msg = "Tarefa alocada para "+pessoa.getNome();
		genericReturn = new GenericReturn(HttpStatus.OK.value(), msg, tarefa);
		
		return genericReturn;
	}
	
	public GenericReturn finalizar(Long id) {
		GenericReturn genericReturn;
		
		Tarefa tarefa = buscarTarefaPorId(id);
		if(tarefa.isFinalizada()) {
			String msg = "Esta tarefa já esta finalizada.";
			genericReturn = new GenericReturn(HttpStatus.OK.value(), msg, tarefa);
			return genericReturn;
		}
		
		tarefa.setFinalizada(true);
		
		tarefaRepository.save(tarefa);
		String msg = "Tarefa finalizada.";
		genericReturn = new GenericReturn(HttpStatus.OK.value(), msg, tarefa);
		
		return genericReturn;
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
