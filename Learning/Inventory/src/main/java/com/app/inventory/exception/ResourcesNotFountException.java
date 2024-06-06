package com.app.inventory.exception;

public class ResourcesNotFountException extends RuntimeException {

	String resource;
	String fieldname;
	int fieldValue;
	public ResourcesNotFountException(String resource, String fieldname, int fieldValue) {
		super(String.format("%s is not fount with %s : %d",resource,fieldname,fieldValue));
		this.resource = resource;
		this.fieldname = fieldname;
		this.fieldValue = fieldValue;
	}
	
}
