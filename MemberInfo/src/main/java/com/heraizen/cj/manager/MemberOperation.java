package com.heraizen.cj.manager;

import java.util.List;
import java.util.Scanner;

import com.heraizen.cj.domain.Member;
import com.heraizen.cj.service.MemberService;
import com.heraizen.cj.service.MemberServiceImpl;

public class MemberOperation {
	MemberService memberService = MemberServiceImpl.getInstance();
	private Scanner sc = null;

	public void start() {
		this.sc = new Scanner(System.in);

		do {
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"1.Add Member\t 2.Delete Member\t3.Search By Id \t4.Search Member By Id or Name\t5.DisplayMemberInSortingOrder\t6.Exit");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter the choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1:
				this.addMember();
				break;
			case 2:
				this.deleteMember();
				break;
			case 3:
				this.viewMemberById();
				break;
			case 4:
				this.searchMemberByIdORName();
				break;
			case 5:
				this.displayMemberSortingOrder();
				break;
			case 6:
				sc.close();
				System.out.println("Bye........");
				System.exit(0);
				break;

			}
		} while (true);
	}

	private void addMember() {
		System.out.println("Enter the name");
		String name = this.sc.nextLine();
		System.out.println("Enter the city");
		String city = this.sc.nextLine();
		System.out.println("Enter the country");
		String country = this.sc.nextLine();
		Member member = Member.builder().name(name).city(city).country(country).build();
		System.out.println("Please wait...............");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		member = memberService.addMemeber(member);
		if (member != null) {
			System.out.println("New member added successfully");
		} else {
			System.out.println("Something wrong add the time of adding new member");
		}

	}

	private void deleteMember() {
		String mid = this.getUserInputEmpno();
		Member member = memberService.searchById(mid);
		while (member == null) {
			System.out.println("There is no member with this member id" + mid);
			mid = this.getUserInputEmpno();
			member = memberService.searchById(mid);
		}
		System.out.println("Are you delete the member (y/n");
		String choice = sc.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			memberService.deleteMember(mid);
			System.out.println("Member  deleted successfully");
		}

	}

	private void viewMemberById() {
		String mid = this.getUserInputEmpno();
		Member member = memberService.searchById(mid);
		if (member != null) {
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20s%-10s%-12s%-12s\n", "Member Id", "Name", "City", "Country");
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20s%-10s%-12s%-12s", member.getMid(), member.getName(), member.getCity(),
					member.getCountry());
			System.out.println();
		} else {
			System.out.println("Member  is not available");
		}
	}

	private void searchMemberByIdORName() {
		String mid = this.getUserInputEmpno();
		System.out.println("Enter the name");
		String name = this.sc.nextLine();
		Member member = memberService.searchByIdOrName(mid, name);
		if (member != null) {
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20s%-10s%-12s%-12s\n", "Member Id", "Name", "City", "Country");
			System.out.print("-------------------------------------------------------------------------------");
			System.out.format("\n%-20s%-10s%-12s%-12s", member.getMid(), member.getName(), member.getCity(),
					member.getCountry());
			System.out.println();
		} else {
			System.out.println("Member  is not available");
		}

	}

	private void displayMemberSortingOrder() {
		System.out.println("Enter Aesc for Ascending Order and Desc for Descending Order ");
		String orderType = sc.nextLine();
		List<Member> memberList = null;
		if (orderType.equalsIgnoreCase("Aesc")) {
			memberList = memberService.displayMembers((m1, m2) -> m1.getName().compareTo(m2.getName()));
		} else if (orderType.equalsIgnoreCase("Desc")) {
			memberList = memberService.displayMembers((m1, m2) -> m2.getName().compareTo(m1.getName()));
		} else {
			System.out.println("Please enter correct input");
		}
		while(!(orderType.equalsIgnoreCase("aesc") || orderType.equalsIgnoreCase("desc")))
		{
			orderType = sc.nextLine();
			if (orderType.equalsIgnoreCase("Aesc")) {
				memberList = memberService.displayMembers((m1, m2) -> m1.getName().compareTo(m2.getName()));
			} else if (orderType.equalsIgnoreCase("Desc")) {
				memberList = memberService.displayMembers((m1, m2) -> m2.getName().compareTo(m1.getName()));
			} else {
				System.out.println("Please enter correct input");
			}
		}
		System.out.print("-------------------------------------------------------------------------------");
		System.out.format("\n%-20s%-10s%-12s%-12s\n", "Member Id", "Name", "City", "Country");
		System.out.print("-------------------------------------------------------------------------------");

		memberList.forEach(member -> System.out.format("\n%-20s%-10s%-12s%-12s", member.getMid(), member.getName(),
				member.getCity(), member.getCountry()));
		System.out.println();{
			
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

	private String getUserInputEmpno() {

		String mid = null;
		System.out.println("Enter the member id :");
		mid = this.sc.nextLine();
		return mid;
	}

}
