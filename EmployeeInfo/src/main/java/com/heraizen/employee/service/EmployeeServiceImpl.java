package com.heraizen.employee.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.heraizen.employee.domain.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	private static EmployeeServiceImpl employeeServiceImpl = null;
	Set<Employee> employeeList = new HashSet<>();

	private EmployeeServiceImpl() {
		this.employeeList.addAll(getSeedData());
	}

	public static EmployeeServiceImpl getInstance() {
		if (employeeServiceImpl == null) {
			synchronized (EmployeeServiceImpl.class) {
				if (employeeServiceImpl == null) {
					employeeServiceImpl = new EmployeeServiceImpl();
				}
			}
		}
		return employeeServiceImpl;
	}

	@Override
	public Employee addEmployee(Employee emp) {
		if (emp != null) {
			this.employeeList.add(emp);
		}
		return emp;
	}

	@Override
	public void deleteEmployee(int empno) {
		Iterator<Employee> iterator = employeeList.iterator();
		while (iterator.hasNext()) {
			Employee emp = iterator.next();
			if (emp.getEmpno() == empno) {
				iterator.remove();
				break;
			}
		}
	}

	@Override
	public Employee viewEmployee(int empno) {
		Employee employee=null;
	    for(Employee emp:employeeList) {
	    	if(emp.getEmpno()==empno) {
	    		employee=emp;	
	    	}
	    }
		return employee;
	}
    
	@Override
	public Set<Employee> viewEmployees() {
		
		return this.employeeList;
	}

	public Set<Employee> getSeedData() {
		Set<Employee> empList = new HashSet<>();
		empList.add(Employee.builder().ename("Badal").job("Software Developer").build());
		empList.add(Employee.builder().ename("Prasom").job("Software Developer").build());
		return empList;
	}
}
