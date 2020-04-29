package com.brq.cursomc.controller.exception;

import java.io.Serializable;

public class MensagemErro implements Serializable {

	private static final long serialVersionUID = 7348722968781308139L;

	private Integer status;
	private String mensagem;
	private Long tempoResposta;
	
	public MensagemErro(Integer status, String mensagem, Long tempoResposta) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.tempoResposta = tempoResposta;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Long getTempoResposta() {
		return tempoResposta;
	}
	public void setTempoResposta(Long tempoResposta) {
		this.tempoResposta = tempoResposta;
	}
	
}
