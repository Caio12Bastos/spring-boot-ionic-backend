package com.brq.cursomc.services.exception;

public class AuthorizationException extends RuntimeException {
	
	private static final long serialVersionUID = -7094119403450885471L;

	public AuthorizationException(String mensagem) {
		super(mensagem);
	}
	
	public AuthorizationException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
