package com.brq.cursomc.controllers.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.brq.cursomc.services.exception.AuthorizationException;
import com.brq.cursomc.services.exception.DataIntegrityException;
import com.brq.cursomc.services.exception.FileException;
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
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<MensagemErro> autorizado(AuthorizationException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.FORBIDDEN.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mensagemErro);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<MensagemErro> file(FileException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.BAD_REQUEST.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<MensagemErro> amazonService(AmazonServiceException excecao, HttpServletRequest request) {
		
		HttpStatus statusCode = HttpStatus.valueOf(excecao.getStatusCode());
		
		MensagemErro mensagemErro = new MensagemErro(
				statusCode.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(statusCode).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<MensagemErro> amazonClient(AmazonClientException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.BAD_REQUEST.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<MensagemErro> amazonS3(AmazonS3Exception excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(
				HttpStatus.BAD_REQUEST.value(), excecao.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
}
