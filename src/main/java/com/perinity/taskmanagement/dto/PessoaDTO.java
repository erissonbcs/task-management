package com.perinity.taskmanagement.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PessoaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nome;
	@NotNull
	private Long idDepartamento;
	
	public PessoaDTO() { }
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getIdDepartamento() {
		return idDepartamento;
	}
	
	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

}
