package com.myapp.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	int fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, int fieldValue) {
		super(String.format("%s not fount witn %s : %d", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	

}
