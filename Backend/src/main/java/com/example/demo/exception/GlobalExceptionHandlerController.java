package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
