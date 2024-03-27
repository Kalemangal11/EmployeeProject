package com.employeeProject.Service;

public class A extends AbstractClass implements B{

//	public A(int a, int b) {
//		super(a, b);
//		
//	}


	int d;
	int e;
	
	@Override
	public void createEmp() {
	int c=a+b;	
	int f=d+e;
	System.out.println(c);
	System.out.println(f);
	}


	@Override
	public String toString() {
		return "A [a=" + a + ", b=" + b + "]";
	}


	
}
