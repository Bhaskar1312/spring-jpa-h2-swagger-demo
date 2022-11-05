package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class GlobalControllerAdvice {
	
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Order> handleConversion(RuntimeException ex) {
		return new ResponseEntity<Order>(/*ex.getMessage(),*/ HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler //(OrderNotFoundException.class) //can be inside controller too or use with @ControllerAdvice
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Exception handleNotFound(OrderNotFoundException ex) {
		return new IllegalArgumentException(ex.getCause());
	}

}
