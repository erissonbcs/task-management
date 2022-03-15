package com.perinity.taskmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	//private List<Tarefa> tarefas;
	
	public Pessoa() { }

//	public Pessoa(Long id, String nome, Departamento departamento, List<Tarefa> tarefas) {
//		super();
//		this.id = id;
//		this.nome = nome;
//		this.departamento = departamento;
//		this.tarefas = tarefas;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

//	public List<Tarefa> getTarefas() {
//		return tarefas;
//	}
//
//	public void setTarefas(List<Tarefa> tarefas) {
//		this.tarefas = tarefas;
//	}
	

}
