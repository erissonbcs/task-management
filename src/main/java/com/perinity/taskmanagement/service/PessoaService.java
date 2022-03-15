package com.perinity.taskmanagement.service;

import org.springframework.stereotype.Service;

import com.perinity.taskmanagement.repository.PessoaRepository;

@Service
public class PessoaService {
	
	PessoaRepository pessoaRepository;

	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
}
