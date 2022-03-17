package com.perinity.taskmanagement.reports;

import java.util.Date;

import com.perinity.taskmanagement.entities.Pessoa;

public class RelatorioMediaHorasGasta {

	private String nome;
	private Date dataInicio;
	private Date dataFim;
	private Pessoa pessoa;
	private int mediaHoras;
	
	public RelatorioMediaHorasGasta() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getMediaHoras() {
		return mediaHoras;
	}

	public void setMediaHoras(int mediaHoras) {
		this.mediaHoras = mediaHoras;
	}

}
