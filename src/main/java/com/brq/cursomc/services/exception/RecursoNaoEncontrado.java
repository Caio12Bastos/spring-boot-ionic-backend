package com.brq.cursomc.services.exception;

public class RecursoNaoEncontrado extends RuntimeException {
	
	private static final long serialVersionUID = -7094119403450885471L;

	public RecursoNaoEncontrado(String mensagem) {
		super(mensagem);
	}
	
	public RecursoNaoEncontrado(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
