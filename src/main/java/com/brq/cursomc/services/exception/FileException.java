package com.brq.cursomc.services.exception;

public class FileException extends RuntimeException {
	
	private static final long serialVersionUID = -7094119403450885471L;

	public FileException(String mensagem) {
		super(mensagem);
	}
	
	public FileException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
