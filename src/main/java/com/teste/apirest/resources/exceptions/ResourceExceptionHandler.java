package com.teste.apirest.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.apirest.services.exceptions.DataBaseException;
import com.teste.apirest.services.exceptions.ResourceNotFoundException;

//Vai fazer o tratamento das excessões
//Interecepta as excessões
@ControllerAdvice
public class ResourceExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	//Vai tratar uma excessão do tipo não encontrada
	//Como fizemos na classe de tratamento de excessão no pacote de services
	@ExceptionHandler(ResourceNotFoundException.class) //Diz que o método vai interceptar este tipo de excessão 
	//Para tratar aqui
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		//Retorna a excessão 404 (NotFound)
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		//Retorna o tipo de excessãoe seu status correto
		return ResponseEntity.status(status).body(err);
	}
	
	
	@ExceptionHandler(DataBaseException.class)
	//Tratamento das exceções de banco
	public  ResponseEntity<StandardError> dataBaseException(DataBaseException e, HttpServletRequest request){
		String error = "Database Error!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
