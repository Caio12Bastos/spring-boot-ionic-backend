package com.brq.cursomc.controllers.exception;

import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends MensagemErro {

	private static final long serialVersionUID = 2641233667820259237L;

	private List<CampoMensagem> erros = new ArrayList<>();
	
	public ErroValidacao(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<CampoMensagem> getErros() {
		return erros;
	}

	public void addErro(String campoMensagem, String mensagem) {
		erros.add(new CampoMensagem(campoMensagem, mensagem));
	}
}
