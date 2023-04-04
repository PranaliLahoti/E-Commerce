package com.java.ecommerce.Exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorStructure> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorStructure ErrorStructure = new ErrorStructure(LocalDate.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorStructure>(ErrorStructure, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, org.springframework.http.HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorStructure ErrorStructure = new ErrorStructure(LocalDate.now(), 
        ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(ErrorStructure, HttpStatus.BAD_REQUEST);
		// return handleExceptionInternal(ex, null, headers, status, request);
	}
}
