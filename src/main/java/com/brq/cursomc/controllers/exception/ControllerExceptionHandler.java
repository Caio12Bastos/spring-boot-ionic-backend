package com.brq.cursomc.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brq.cursomc.services.exception.DataIntegrityException;
import com.brq.cursomc.services.exception.RecursoNaoEncontrado;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(RecursoNaoEncontrado.class)
	public ResponseEntity<MensagemErro> recursoNaoEncontrado(RecursoNaoEncontrado excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.NOT_FOUND.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<MensagemErro> dataIntegrity(DataIntegrityException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.BAD_REQUEST.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MensagemErro> validacao(MethodArgumentNotValidException excecao, HttpServletRequest request) {
		
		ErroValidacao erroValidacao = new ErroValidacao(
				HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError erro : excecao.getBindingResult().getFieldErrors()) {
			erroValidacao.addErro(erro.getField(), erro.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroValidacao);
	}
}
