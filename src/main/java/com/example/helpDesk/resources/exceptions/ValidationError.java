package com.example.helpDesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

//
// Essa classe vai estender a classe de erro padrão "StantardError"
//
public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList<>();
	public ValidationError() {
		super();
	}
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	public List<FieldMessage> getErrors() {
		return errors;
	}
	public void addErrors (String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
}
//