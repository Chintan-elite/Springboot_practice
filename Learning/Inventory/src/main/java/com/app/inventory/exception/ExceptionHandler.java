package com.app.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.app.inventory.payload.APIResponse;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(ResourcesNotFountException.class)
	public ResponseEntity<APIResponse> resourcenotexceptionhandler(ResourcesNotFountException ex)
	{
		String msg = ex.getMessage();
		APIResponse apiResponse = new APIResponse(msg,false);
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
