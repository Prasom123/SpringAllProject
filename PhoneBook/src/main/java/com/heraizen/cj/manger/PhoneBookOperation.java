package com.heraizen.cj.manger;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heraizen.cj.service.PhoneBookService;
import com.heraizen.cj.service.PhoneBookServiceImpl;

public class PhoneBookOperation {
	PhoneBookService phoneBookService = PhoneBookServiceImpl.getInstance();
	private Scanner sc = null;

	public void start() {
		this.sc = new Scanner(System.in);
		
		do {
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("1.Add new phone\t 2.Search name\t3.Quit");
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println("Enter the choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1:
				this.addDetails();
				break;
			case 2:
				this.getName();
				break;

			case 3:
				sc.close();
				System.out.println("Bye........");
				System.exit(0);
				break;

			}
		} while (true);
	}

	private void getName() {
		String mobileNumber = this.getValidMobileInput();
		String name=phoneBookService.getName(mobileNumber);
		if(name!=null) {
		System.out.println(name+" : "+mobileNumber);
		
		 } else {
			 System.out.println("There is no contact name with this mobile number: "+mobileNumber);
		 }
		

	}

	private void addDetails() {
		System.out.println("Enter the Name");
		String name = sc.nextLine();
		String mobileNumber = this.getValidMobileInput();
		phoneBookService.addDetails(mobileNumber, name);

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

	private String getValidMobileInput() {
		System.out.println("Enter the mobile number :");
		String mobile = this.sc.nextLine();
		while (!this.checkContainDigits(mobile)) {
			System.out.println("Enter the valid mobile number :");
			mobile = this.sc.nextLine();
		}
		return mobile;
	}

	private boolean checkContainDigits(String mobile) {
		Pattern pattern = Pattern.compile("\\d{10}");
		Matcher matcher = pattern.matcher(mobile);
		boolean isvalid = matcher.matches();
		return isvalid;
	}

}
