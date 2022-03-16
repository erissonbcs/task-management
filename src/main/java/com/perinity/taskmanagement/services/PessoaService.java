package com.perinity.taskmanagement.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.dto.PessoaDTO;
import com.perinity.taskmanagement.entities.Departamento;
import com.perinity.taskmanagement.entities.Pessoa;
import com.perinity.taskmanagement.repositories.DepartamentoRepository;
import com.perinity.taskmanagement.repositories.PessoaRepository;
import com.perinity.taskmanagement.services.exceptions.EntityNotFoundException;

@Service
public class PessoaService {
	
	PessoaRepository pessoaRepository;
	DepartamentoRepository departamentoRepository;

	public PessoaService(PessoaRepository pessoaRepository, DepartamentoRepository departamentoRepository) {
		this.pessoaRepository = pessoaRepository;
		this.departamentoRepository = departamentoRepository;
	}
	
    public Pessoa salvar(PessoaDTO pessoaDTO) {
    	Pessoa pessoa = new Pessoa(pessoaDTO.getNome(), new Departamento(pessoaDTO.getIdDepartamento()));
    	buscarDepartamentoPorId(pessoaDTO.getIdDepartamento());
    	
    	return pessoaRepository.save(pessoa);
    }
	
    private Departamento buscarDepartamentoPorId(Long id) {
    	Optional<Departamento> obj = departamentoRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Departamento informado não foi encontrado."));
    }
    
    public Pessoa atualizar(Long id, PessoaDTO pessoaDTO) {
    	Pessoa pessoa = buscarPessoaPorId(id);
    	Departamento depatamento = buscarDepartamentoPorId(pessoaDTO.getIdDepartamento());
    	
    	pessoa.setDepartamento(depatamento);
    	pessoa.setNome(pessoaDTO.getNome());
  
    	return pessoaRepository.save(pessoa);
    }
    
    private Pessoa buscarPessoaPorId(Long id) {
    	Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new EntityNotFoundException("Pessoa informada não foi encontrada."));
    }
    
    public String deletar(Long id) {
    	Pessoa pessoa = buscarPessoaPorId(id);
    	pessoaRepository.delete(pessoa);
    	
    	return "Pessoa deletada com sucesso!";	
    }
}
