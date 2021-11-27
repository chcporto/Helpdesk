package com.example.helpDesk.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.helpDesk.services.exceptions.DataIntegrityViolationException;
import com.example.helpDesk.services.exceptions.ObjectNotFoundException;
import com.example.helpDesk.services.exceptions.StandardError;

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

}
