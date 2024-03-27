package com.employeeProject.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotFountException extends RuntimeException {

	String resourceName;
	String fldName;
	Integer fieldValue;
	
	public ResourceNotFountException(String resourceName,String fldName,Integer fieldValue) {
		super(String.format("%s not found with %s:%s", resourceName, fldName,fieldValue));
		this.resourceName=resourceName;
		this.fldName=fldName;
		this.fieldValue=fieldValue;
	}
}
