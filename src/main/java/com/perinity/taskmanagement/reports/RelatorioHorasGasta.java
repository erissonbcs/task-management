package com.perinity.taskmanagement.reports;

import com.perinity.taskmanagement.entities.Departamento;

public class RelatorioHorasGasta {
	
	private String nome;
	
	private Departamento departamento;
	
	private int totalHoras;

	public RelatorioHorasGasta() {
		
	}

	public RelatorioHorasGasta(String nome, Departamento departamento, int totalHoras) {
		super();
		this.nome = nome;
		this.departamento = departamento;
		this.totalHoras = totalHoras;
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

	public int getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}
	
}
