package com.heraizen.employee.service;

import java.util.Set;

import com.heraizen.employee.domain.Employee;

public interface EmployeeService {
 public Employee addEmployee(Employee emp);
 public void	deleteEmployee(int empno);
 public Employee viewEmployee(int empno);
 public Set<Employee>	viewEmployees();
}
