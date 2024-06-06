package com.app.inventory.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			 String fieldName = ((FieldError)error).getField();
			 String message = error.getDefaultMessage();
			 resp.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
	}
}
