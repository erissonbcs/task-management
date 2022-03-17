package com.perinity.taskmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.dto.DepartamentoDTO;
import com.perinity.taskmanagement.entities.Departamento;
import com.perinity.taskmanagement.repositories.DepartamentoRepository;
import com.perinity.taskmanagement.repositories.PessoaRepository;
import com.perinity.taskmanagement.repositories.TarefaRepository;

@Service
public class DepartamentoService {
	
	DepartamentoRepository departamentoRepository;
	PessoaRepository pessoaRepository;
	TarefaRepository tarefaRepository;
	
	public DepartamentoService(DepartamentoRepository departamentoRepository, PessoaRepository pessoaRepository,
			TarefaRepository tarefaRepository) {
		this.pessoaRepository = pessoaRepository;
		this.departamentoRepository = departamentoRepository;
		this.tarefaRepository = tarefaRepository;
	}
	
	public List<DepartamentoDTO> listarDepartamentos(){
		List<Departamento> departamentos = departamentoRepository.findAll();
		List<DepartamentoDTO> listaDepartamentos = new ArrayList<DepartamentoDTO>();
		DepartamentoDTO departamentoDTO;
		
		int qtdePessoas = 0;
		int qtdeTarefas = 0;
		
		for(Departamento departamento: departamentos) {
			qtdePessoas = pessoaRepository.findByIdDepartamento(departamento.getId()).size();
			qtdeTarefas = tarefaRepository.findByIdDepartamento(departamento.getId()).size();
			departamentoDTO = new DepartamentoDTO(departamento.getTitulo(), qtdePessoas, qtdeTarefas);
			listaDepartamentos.add(departamentoDTO);
		}
		
		return listaDepartamentos;
	}
}
