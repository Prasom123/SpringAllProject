package com.heraizen.employee.domain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
  private int empno;
  private String ename;
  private String job;
  
  private Employee(int id,String ename, String job) {
	  this.empno=IDGenerator.getNewId();
	  this.ename=ename;
	  this.job=job;
  }
}
