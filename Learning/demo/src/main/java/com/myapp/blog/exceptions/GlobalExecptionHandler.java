package com.myapp.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myapp.blog.payloads.APIResponse;

@RestControllerAdvice
public class GlobalExecptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse> resourcenotfoundehendler(ResourceNotFoundException ex)
	{
		String msg = ex.getMessage();
		APIResponse response = new APIResponse(msg,false);
		return new ResponseEntity<APIResponse>(response,HttpStatus.NOT_FOUND);
	}
}
