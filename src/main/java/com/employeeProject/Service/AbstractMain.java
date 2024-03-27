package com.employeeProject.Service;

public class AbstractMain {

	public static void main(String[] args) {
		
		A em=new A();
		System.out.println(em);
		em.createEmp();
		
//		em.a=10;
//		em.b=20;
		System.out.println(em);
		em.createEmp();
		
		B.sum();
		}
}
