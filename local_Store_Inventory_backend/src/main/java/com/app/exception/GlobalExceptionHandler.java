package com.app.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override 
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		StringBuilder sb = new StringBuilder("Validations error message: ");
		ex.getBindingResult().getFieldErrors().forEach(fieldError -> sb.append(fieldError.getDefaultMessage() + " , "));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(sb.toString(),false));
	}
	

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeException(RuntimeException e){
		System.out.println(" in handle rruntime global exception" + e);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Something went wrong buddy: "+e.getMessage(),false));
	}
	
	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<?> handleAuthorizationDeniedException(AuthorizationDeniedException e){
		System.out.println(" in handle AuthorizationDeniedException global exception" + e);
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse("You are Unauthorize : "+ e.getMessage(), false));
	}
}