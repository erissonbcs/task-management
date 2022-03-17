package com.perinity.taskmanagement.dto;

import java.io.Serializable;

public class DepartamentoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private int qtdePessoas;
	private int qtdeTarefas;
	
	public DepartamentoDTO(String titulo, int qtdePessoas, int qtdeTarefas) {
		this.titulo = titulo;
		this.qtdePessoas = qtdePessoas;
		this.qtdeTarefas = qtdeTarefas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getQtdePessoas() {
		return qtdePessoas;
	}

	public void setQtdePessoas(int qtdePessoas) {
		this.qtdePessoas = qtdePessoas;
	}

	public int getQtdeTarefas() {
		return qtdeTarefas;
	}

	public void setQtdeTarefas(int qtdeTarefas) {
		this.qtdeTarefas = qtdeTarefas;
	}
	
}
