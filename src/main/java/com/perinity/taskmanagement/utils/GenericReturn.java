package com.perinity.taskmanagement.utils;

public class GenericReturn {
	
	private int status;
	
	private String mensagem;
	
	public Object objeto;
	
	public GenericReturn() {
		
	}

	public GenericReturn(int status, String mensagem, Object objeto) {
		this.status = status;
		this.mensagem = mensagem;
		this.objeto = objeto;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
}
