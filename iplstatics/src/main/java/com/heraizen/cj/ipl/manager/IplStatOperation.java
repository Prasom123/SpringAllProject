package com.heraizen.cj.ipl.manager;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.heraizen.cj.ipl.dto.PlayerDTO;
import com.heraizen.cj.ipl.dto.RoleAmountDTO;
import com.heraizen.cj.ipl.dto.RoleCountDTO;
import com.heraizen.cj.ipl.dto.TeamAmountDTO;
import com.heraizen.cj.ipl.dto.TeamDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;
import com.heraizen.cj.ipl.service.IplServiceImpl;

public class IplStatOperation {
	private static IplServiceImpl iplServiceImplObj = IplServiceImpl.getInstance();
	Scanner sc = new Scanner(System.in);

	public void start() {

		do {
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"1.View Team Labels \t 2.Get Player By Labels \t3. Get Role Count By Label  \t4.Get Player Label and By Role \t5 View Team Details \n 6.View Amount Spented On Role By Label "
							+ "\t 7.View Amount Spented By Team Label" + "\t 8.View Max Paid Player For Each Role "
							+ " \t 9. View All Players" + "\t10.Exit");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter the choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1:
				this.viewTeamLabels();
				break;
			case 2:
				this.getPlayersByLabel();
				break;
			case 3:
				this.getRoleCountBylabel();
				break;
			case 4:
				this.getPlayerByLabelAndRole();
				break;
			case 5:
				this.viewTeamDetails();
				break;
			case 6:
				this.viewAmountSpentedByRoleAndLabel();
				break;
			case 7:
				this.viewAmountSpentedByTeam();
				break;
			case 8:
				this.viewMaxPaidPlayerForEachRole();
				break;
			case 9:
				this.viewAllPlayers();
				break;
			case 10:
				sc.close();
				System.out.println("Bye........");
				System.exit(0);
				break;

			}
		} while (true);

	}

	private void viewAmountSpentedByTeam() {
		System.out.println("Enter the label");
		String label = sc.nextLine();
		TeamAmountDTO spentedTeamAmount = null;
		spentedTeamAmount = iplServiceImplObj.getAmountSpentByTeam(label);
		if (spentedTeamAmount != null) {
			System.out.println("-------------------------------------------");
			System.out.format("%-20s%-20s-\n", "Team Name", "Amount");
			System.out.println("-------------------------------------------");
			System.out.format("%-20s%-20s-\n", spentedTeamAmount.getLabel().toUpperCase(),
					spentedTeamAmount.getAmount());
			System.out.println();
		} else {
			System.out.println("Team Label is not available");
		}

	}

	private void viewTeamDetails() {
		List<TeamDTO> teamDetails = iplServiceImplObj.getTeamDetails();
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.format("%-30s%-30s%-50s%-20s\n", "Team Name", "Coach Name", "Home Ground", "Team Label");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------------------");
		teamDetails.forEach(team -> {
			System.out.format("%-30s%-30s%-50s%-20s\n", team.getName(), team.getCoach(), team.getHome(),
					team.getLabel());

		});
	}

	private void viewAllPlayers() {
		List<PlayerDTO> allPlayers = iplServiceImplObj.getAllPlayers();
		this.viewPlayers(allPlayers);
	}

	private void viewMaxPaidPlayerForEachRole() {
		List<PlayerDTO> players = iplServiceImplObj.getMaxPaidPlayerOnEachRole();
		this.viewPlayers(players);

	}

	private void viewAmountSpentedByRoleAndLabel() {
		System.out.println("Enter the label");
		String label = sc.nextLine();
		List<RoleAmountDTO> amountSpent = new ArrayList<>();
		amountSpent = iplServiceImplObj.getAmountSpentOnRoleByLabel(label);
		if (amountSpent.size() != 0) {

			System.out.println("-------------------------------------------");
			System.out.format("%-20s%-20s\n", "Role", "Amount");
			System.out.println("-------------------------------------------");
			amountSpent.forEach(ele -> {
				System.out.format("%-20s%-20s\n", ele.getRole(), ele.getAmount());
			});
		} else {
			System.out.println("Team label is not available");
		}

	}

	private void getPlayerByLabelAndRole() {
		System.out.println("Enter the Label");
		String label = sc.nextLine();
		System.out.println("Enter the Role");
		String role = sc.nextLine();
		List<PlayerDTO> players = new ArrayList<>();
		players = iplServiceImplObj.getPlayerByLabelAndRole(label, role);
		if (players.size() != 0) {
			this.viewPlayers(players);
		} else {
			System.out.println("Team label is not available");
		}
	}

	private void getRoleCountBylabel() {
		System.out.println("Enter the label");
		String label = sc.nextLine();
		List<RoleCountDTO> roleCount = new ArrayList<>();
		roleCount = iplServiceImplObj.getRoleCountByLabel(label);
		if (roleCount.size() != 0) {
			System.out.println("-------------------------------------------");
			System.out.format("%-20s%-20s\n", "Role", "Number Of Players");
			System.out.println("-------------------------------------------");
			roleCount.stream().forEach(ele -> {
				System.out.format("%-20s%-20s\n", ele.getRole(), ele.getCount());
			});
		} else {
			System.out.println("Team label is not available");
		}
	}

	private void getPlayersByLabel() {
		System.out.println("Enter the label");
		String label = sc.nextLine();
		List<PlayerDTO> players = new ArrayList<>();
		players = iplServiceImplObj.getPlayerByLabel(label);
		if (players.size() != 0) {
			this.viewPlayers(players);
		} else {
			System.out.println("Team label is not available");
		}

	}

	private void viewTeamLabels() {
		TeamLabelsDTO teamLables = iplServiceImplObj.getTeamLabels();
		System.out.println("Team Label");
		teamLables.getLabels().forEach(team -> {
			System.out.println(team);
		});

	}

	private void viewPlayers(List<PlayerDTO> players) {
		System.out
				.println("-------------------------------------------------------------------------------------------");
		System.out.format("%-30s%-20s%-20s\n", "Player Name", "Role", "Price");
		System.out
				.println("-------------------------------------------------------------------------------------------");
		players.stream().forEach(p -> {
			System.out.format("%-30s%-20s%-20s\n", p.getName(), p.getRole(), p.getPrice());
		});

		System.out.println();
	}

	private int getUserChoice() {
		boolean isNumber = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		while (!(choice > 0 && choice < 11 || isNumber)) {
			System.out.println("Choice must be 1 to 10 only");
			try {
				choice = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		return choice;
	}
}
