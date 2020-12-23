package com.heraizen.cj.ipl.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.heraizen.cj.ipl.domain.Players;
import com.heraizen.cj.ipl.domain.Team;
import com.heraizen.cj.ipl.dto.PlayerDTO;
import com.heraizen.cj.ipl.dto.TeamAmountDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;
import com.heraizen.cj.ipl.service.IplNewService;
import com.heraizen.cj.ipl.service.IplNewServiceImpl;

public class IplStatNewOperation {
	private static IplNewService iplNewServiceImplObj = IplNewServiceImpl.getInstance();
	Scanner sc = new Scanner(System.in);

	public void start() {
		do {
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(
					"1.View Team Labels \t 2.Add Team \t 3.Amount SpentedByEach Team \t 4.Add Player in Team By label\t"
					+ "5.Get Player By Label \t6.Role Count By Label 7.Exit");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter the choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1:
				this.viewTeamLabels();
				break;
			case 2:
				this.addTeam();
				break;
			case 3:
				this.amountSpentedByEachTeam();
				break;
			case 4:
				this.addPlayerByLabel();
				break;
			case 5:
				this.getPlayerByLabel();
				break;
			case 6:
				this.roleCountByLabel();
				break;

			case 7:
				sc.close();
				System.out.println("Bye........");
				System.exit(0);
				break;

			}
		} while (true);

	}

	private void getPlayerByLabel() {
		System.out.println("Enter the label");
		String label=sc.nextLine();
	 List<PlayerDTO> players=iplNewServiceImplObj.getPlayerByLabel(label);
	 if(players!=null) {
	 System.out.println(players);
	 System.out.println("-----------------------------------------------------");
	  System.out.format("%-20s%-20s%-20s\n", "Name", "Role","Amount");
	  System.out.println("-----------------------------------------------------");
       players.stream().forEach(player -> {

   	   System.out.format("%-20s%-20s%-20f\n", player.getName(), player.getRole(), player.getPrice());
      });
	 } else {
		 System.out.println("Team is not available");
	 }
		
	}
	private void roleCountByLabel() {
		System.out.println("Enter the label");
		String label=sc.nextLine();
	 iplNewServiceImplObj.getRoleCountByLabel(label);
//	 if(players!=null) {
//	 System.out.println(players);
//	 System.out.println("-----------------------------------------------------");
//	  System.out.format("%-20s%-20s%-20s\n", "Name", "Role","Amount");
//	  System.out.println("-----------------------------------------------------");
//       players.stream().forEach(player -> {
//
//   	   System.out.format("%-20s%-20s%-20f\n", player.getName(), player.getRole(), player.getPrice());
//      });
//	 } else {
//		 System.out.println("Team is not available");
//	 }
		
	}

	private void viewTeamLabels() {
		TeamLabelsDTO teamLabelDTO=iplNewServiceImplObj.getTeamLabel();
		List<String> teamLabels = teamLabelDTO.getLabels();
		System.out.println("Team Label");
		teamLabels.forEach(label-> {
			 System.out.println(label);
		 });

	}

	private void addTeam() {
		System.out.println("Enter the city");
		String city=sc.nextLine();
		System.out.println("Enter the Coach Name");
		String coach=sc.nextLine();
		System.out.println("Enter the home ground name");
		String home=sc.nextLine();
		System.out.println("Enter the team name");
		String name=sc.nextLine();
		System.out.println("Enter the  label name");
		List<Players> players=new ArrayList<>();
		String label=sc.nextLine();
		Team team=Team.builder().city(city).coach(coach).home(home).name(name).label(label).players(players).build();
		  team=iplNewServiceImplObj.addTeam(team);
		 System.out.println("Please Wait...................");
		 try {
			 Thread.sleep(3000);
		 } catch (InterruptedException e) {
			System.out.println(e);
		 }
		 if(team!=null) {
			 System.out.println("Successfully Team Added");
		 } else {
			 System.out.println("Something is going at the time adding team ");
		 }
		 
		
	}

	private void amountSpentedByEachTeam() {
	  List<TeamAmountDTO> listAmountSpentedTeam=iplNewServiceImplObj.getAmountSpentedByEachTeam();
	  System.out.println("-----------------------------------------------------");
	  System.out.format("%-20s%-20s\n", "Label", "Amount");
	  System.out.println("-----------------------------------------------------");
       listAmountSpentedTeam.stream().forEach(team -> {
    	   team.getLabel();
    	   team.getAmount();
    	   System.out.format("%-20s%-20f\n", team.getLabel(), team.getAmount());
       });
	}

	private void addPlayerByLabel() {
		
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
