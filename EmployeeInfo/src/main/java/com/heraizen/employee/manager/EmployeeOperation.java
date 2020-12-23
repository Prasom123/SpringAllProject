package com.heraizen.employee.manager;

import java.util.Scanner;
import java.util.Set;

import com.heraizen.employee.domain.Employee;
import com.heraizen.employee.service.EmployeeService;
import com.heraizen.employee.service.EmployeeServiceImpl;

public class EmployeeOperation {
	private Scanner sc = null;
	EmployeeService empService = EmployeeServiceImpl.getInstance();

	public void start() {
		this.sc = new Scanner(System.in);
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("1.Add Employee\t 2.View Employee\t3.Get Employee By Empno\t4.Delete Employee\t5.Exit");
		System.out.println("--------------------------------------------------------------------------------------");
		do {
			System.out.println("Enter the choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1:
				this.addEmployee();
				break;
			case 2:
				this.viewEmployee();
				break;
			case 3:
				this.viewEmployeeById();
				break;
			case 4:
				this.deleteEmployee();
				break;
			case 5:
				sc.close();
				System.out.println("Bye........");
				System.exit(0);
				break;

			}
		} while (true);
	}

	private void addEmployee() {
		System.out.println("Enter the name");
		String ename = sc.nextLine();
		System.out.println("Enter the Job");
		String jobtype = sc.nextLine();
		Employee employee = Employee.builder().ename(ename).job(jobtype).build();
		System.out.println("Wait..............");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		employee = empService.addEmployee(employee);
		if (employee != null) {
			System.out.println("Employee Added Successfully");
		} else {
			System.out.println("Some thing wrong while adding employee");
		}
	}

	private void viewEmployee() {
		Set<Employee> empList = empService.viewEmployees();
		System.out.print("-------------------------------------------------------------------------------");
		System.out.format("\n%-20s%-10s%-12s\n", "Employee No", "Name", "Job Type");
		System.out.print("-------------------------------------------------------------------------------");
		empList.forEach(emp -> {

			System.out.format("\n%-20d%-10s%-12s", emp.getEmpno(), emp.getEname(), emp.getJob());
		});
	}

	private void viewEmployeeById() {
		int empno = this.getUserInputEmpno();
		Employee employee = empService.viewEmployee(empno);
		if (employee != null) {
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20s%-10s%-12s\n", "Employee No", "Name", "Job Type");
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20d%-10s%-12s", employee.getEmpno(), employee.getEname(), employee.getJob());
		} else {
			System.out.println("Employee Number is not available");
		}

	}

	private void deleteEmployee() {
		int empno=getUserInputEmpno();
		Employee emp=empService.viewEmployee(empno);
		while(emp==null){
			System.out.println("With the entred employee id, there is no employee...");
			empno = getUserInputEmpno();
			emp=empService.viewEmployee(empno);
		}
		
		System.out.println("Are sure do want to delete employee (y/n) ");
		String choice=sc.nextLine();
		if(choice.equalsIgnoreCase("y")){
			empService.deleteEmployee(empno);
			System.out.println("Employee with empno :"+empno+" is deleted successfuly ");
		}
	}

	private int getUserChoice() {
		boolean isNumber = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		while (!(choice > 0 && choice < 8 || isNumber)) {
			System.out.println("Choice must be 1 to 7 only");
			try {
				choice = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		return choice;
	}

	private int getUserInputEmpno() {
		boolean isNumber = false;
		int empno = 0;
		System.out.println("Enter the Empno :");
		try {
			empno = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = true;
		}
		while (empno <= 0 && isNumber) {
			System.out.println("Enter valid Empno : must be > 0");
			try {
				empno = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = true;
			}
		}
		return empno;
	}
}
