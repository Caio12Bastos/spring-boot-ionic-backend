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
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Nao encontrado", excecao.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagemErro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<MensagemErro> dataIntegrity(DataIntegrityException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MensagemErro> validacao(MethodArgumentNotValidException excecao, HttpServletRequest request) {
		
		ErroValidacao erroValidacao = new ErroValidacao(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				"Erro de validação", excecao.getMessage(), request.getRequestURI());
		
		for(FieldError erro : excecao.getBindingResult().getFieldErrors()) {
			erroValidacao.addErro(erro.getField(), erro.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroValidacao);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<MensagemErro> autorizado(AuthorizationException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Acesso negado", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mensagemErro);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<MensagemErro> file(FileException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro de arquivo", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<MensagemErro> amazonService(AmazonServiceException excecao, HttpServletRequest request) {
		
		HttpStatus statusCode = HttpStatus.valueOf(excecao.getStatusCode());
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), statusCode.value(),
				"Erro Amazon service", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(statusCode).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<MensagemErro> amazonClient(AmazonClientException excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro Amazon cliet", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<MensagemErro> amazonS3(AmazonS3Exception excecao, HttpServletRequest request) {
		
		MensagemErro mensagemErro = new MensagemErro(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro S3", excecao.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
	}
}
