package com.employeeProject.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFountException extends RuntimeException {

	String resourceName;
	String fieldName;
	Integer fieldValue;
	
	public ResourceNotFountException(String resourceName,String fieldName,Integer fieldValue) {
		super(String.format("%s not found with %s:%s", resourceName, fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
}
