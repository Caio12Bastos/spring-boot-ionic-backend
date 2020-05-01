package com.brq.cursomc.controllers.exception;

import java.io.Serializable;

public class CampoMensagem implements Serializable {

	private static final long serialVersionUID = -7058560077310491829L;

	private String campoMensagem;
	private String mensagem;
	
	public CampoMensagem() {
		
	}

	public CampoMensagem(String campoMensagem, String mensagem) {
		super();
		this.campoMensagem = campoMensagem;
		this.mensagem = mensagem;
	}

	public String getCampoMensagem() {
		return campoMensagem;
	}

	public void setCampoMensagem(String campoMensagem) {
		this.campoMensagem = campoMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}

