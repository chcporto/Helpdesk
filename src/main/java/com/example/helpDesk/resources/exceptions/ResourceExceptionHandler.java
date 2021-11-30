package com.example.helpDesk.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	// anotação usada para escutar as exceções geradas
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
			HttpServletRequest request) {
		/// Agora é preciso instanciar o object "StandardError"
//		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
//				"Objetct Not Found", ex.getMessage(), request.getRequestURI());
		StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Objetct Not Found", ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

		@ExceptionHandler(DataIntegrityViolationException.class)
		public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
				HttpServletRequest request) {
			StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					"Violação de dados", ex.getMessage(), request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
				HttpServletRequest request) {
			ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					"Violation error", "Erro na validação dos campos", request.getRequestURI());
			for(FieldError x : ex.getBindingResult().getFieldErrors()) {
				errors.addErrors(x.getField(),x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

		}
		

}
