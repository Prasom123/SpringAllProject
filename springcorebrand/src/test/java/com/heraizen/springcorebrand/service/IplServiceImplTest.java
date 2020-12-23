package com.heraizen.springcorebrand.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.springcorebrand.domain.Players;
import com.heraizen.springcorebrand.domain.Team;
import com.heraizen.springcorebrand.dto.PlayerDTO;
import com.heraizen.springcorebrand.dto.RoleCountDTO;
import com.heraizen.springcorebrand.dto.TeamDTO;
import com.heraizen.springcorebrand.dto.TeamLabelsDTO;

@SpringBootTest
public class IplServiceImplTest {
	
	private IplService iplService;
	
	List<Team> getTeams() {
		List<Players> miPlayers=new ArrayList<Players>();

		Players miplayer1=Players.builder().name("Rohit Sharma").role("Batsman").price(150000000).build();
		Players miplayer2=Players.builder().name("Surya Kumar yadav").role("Batsman").price(50000000).build();
		Players miplayer3=Players.builder().name("Ishan Kishan").role("Wicket Keeper").price(62000000).build();
		Players miplayer4=Players.builder().name("Hardik Pandya").role("All-Rounder").price(110000000).build();
		Players miplayer5=Players.builder().name("Krunal Pandya").role("All-Rounder").price(88000000).build();
		Players miplayer6=Players.builder().name("Rahul Chahar").role("All-Rounder").price(19000000).build();
		Players miplayer7=Players.builder().name("Jasprit Bumrah").role("Bowler").price(70000000).build();
		Players miplayer8=Players.builder().name("Trent Boult").role("Bowler").price(32000000).build();
		Players miplayer9=Players.builder().name("Nathan Coulter-Nile").role("Bowler").price(80000000).build();
		Players miplayer10=Players.builder().name("Ankul Roy").role("Batsman").price(2000000).build();
		Players miplayer11=Players.builder().name("Anmolpreet Singh (R)").role("Batsman").price(8000000).build();
		miPlayers.addAll(Arrays.asList(miplayer1,miplayer2, miplayer3, miplayer4, miplayer5,miplayer6, miplayer7, miplayer8,miplayer9, miplayer10, miplayer11));
		
		List<Players> rcbPlayers=new ArrayList<Players>();
		
		Players rcbplayer1=Players.builder().name("Virat Kohli (R)").role("Batsman").price(170000000).build();
		Players rcbplayer2=Players.builder().name("Devdutt Padikkal (R)").role("Batsman").price(2000000).build();
		Players rcbplayer3=Players.builder().name("AB de Villiers (R)").role("Wicket Keeper").price(110000000).build();
		Players rcbplayer4=Players.builder().name("Pavan Deshpande").role("All-Rounder").price(2000000).build();
		Players rcbplayer5=Players.builder().name("Christopher Morris").role("All-Rounder").price(100000000).build();
		Players rcbplayer6=Players.builder().name("Navdeep Saini (R)").role("Bowler").price(30000000).build();
		Players rcbplayer7=Players.builder().name("Umesh Yadav").role("Bowler").price(42000000).build();
		Players rcbplayer8=Players.builder().name("Washington Sundar (R)").role("Bowler").price(32000000).build();
		Players rcbplayer9=Players.builder().name("Yuzvendra Chahal (R)").role("Bowler").price(60000000).build();
		Players rcbplayer10=Players.builder().name("Aaron Finch").role("Batsman").price(44000000).build();
		Players rcbplayer11=Players.builder().name("Pawan Negi (R)").role("All-Rounder").price(10000000).build();
		rcbPlayers.addAll(Arrays.asList(rcbplayer1,rcbplayer2, rcbplayer3, rcbplayer4, rcbplayer5,rcbplayer6, rcbplayer7, rcbplayer8,rcbplayer9, rcbplayer10, rcbplayer11));
		
	    
		List<Team> teams =new ArrayList<Team>(); 
	    Team t1=	Team.builder().name("Mumbai Indians").city("Mumbai").coach("Mahila Jayvardhane").home("Wankehede").label("MI").players(miPlayers).build();
	    Team t2=	Team.builder().name("Royal Challengers Bangalore").city("Bengaluru, Karnataka").coach("Simon Katich").home("M. Chinnaswamy Stadium").label("RCB").players(rcbPlayers).build();
	    teams.addAll(Arrays.asList(t1,t2));
		return teams;
	}
	
	@BeforeEach
	void init() {
	  iplService=IplServiceImpl.getInstance();
	  iplService.addTeam(getTeams());
	  
	}
	
	@AfterEach
	void destroy() {
		iplService.removeAllTeam();
	}
	
	@DisplayName("Get Team Label")
	@Test
	void testTeamLabel() {
		 List<String> expectedLabels=new ArrayList<>();
		 expectedLabels.addAll(Arrays.asList("MI","RCB"));
		  TeamLabelsDTO teamlabels = iplService.getTeamLabels();
	    Assertions.assertEquals(expectedLabels, teamlabels.getLabels());
	}
	@DisplayName("Get Player By label Valid label")
	@Test
	void getPlayerByCorrectlabelTest() {
		List<PlayerDTO> expectedPlayers=new ArrayList<PlayerDTO>();
		
		PlayerDTO rcbplayer1=PlayerDTO.builder().name("Virat Kohli (R)").role("Batsman").price(170000000).build();
		PlayerDTO rcbplayer2=PlayerDTO.builder().name("Devdutt Padikkal (R)").role("Batsman").price(2000000).build();
		PlayerDTO rcbplayer3=PlayerDTO.builder().name("AB de Villiers (R)").role("Wicket Keeper").price(110000000).build();
		PlayerDTO rcbplayer4=PlayerDTO.builder().name("Pavan Deshpande").role("All-Rounder").price(2000000).build();
		PlayerDTO rcbplayer5=PlayerDTO.builder().name("Christopher Morris").role("All-Rounder").price(100000000).build();
		PlayerDTO rcbplayer6=PlayerDTO.builder().name("Navdeep Saini (R)").role("Bowler").price(30000000).build();
		PlayerDTO rcbplayer7=PlayerDTO.builder().name("Umesh Yadav").role("Bowler").price(42000000).build();
		PlayerDTO rcbplayer8=PlayerDTO.builder().name("Washington Sundar (R)").role("Bowler").price(32000000).build();
		PlayerDTO rcbplayer9=PlayerDTO.builder().name("Yuzvendra Chahal (R)").role("Bowler").price(60000000).build();
		PlayerDTO rcbplayer10=PlayerDTO.builder().name("Aaron Finch").role("Batsman").price(44000000).build();
		PlayerDTO rcbplayer11=PlayerDTO.builder().name("Pawan Negi (R)").role("All-Rounder").price(10000000).build();
		
		expectedPlayers.addAll(Arrays.asList(rcbplayer1,rcbplayer2, rcbplayer3, rcbplayer4, rcbplayer5,rcbplayer6, rcbplayer7, rcbplayer8,rcbplayer9, rcbplayer10, rcbplayer11));
		List<PlayerDTO> playerList = iplService.getPlayerByLabel("RCB");
		Assertions.assertEquals(expectedPlayers, playerList);
	}
	
	@DisplayName("Enter invalid label")
	@Test
	void getPlayerByIncorrecctlabelTest() {
		
		Assertions.assertThrows(IllegalArgumentException.class, ()-> iplService.getPlayerByLabel("Pt"));
	}
	@DisplayName("Role Count By Entered Valid Label")
	@Test
	void  testGetRoleCountByValidLabel() {
		
		RoleCountDTO allRounderCount = RoleCountDTO.builder().role("All-Rounder").count(3).build();
		RoleCountDTO batmanCount = RoleCountDTO.builder().role("Batsman").count(4).build();
		RoleCountDTO bowlerCount = RoleCountDTO.builder().role("Bowler").count(3).build();
		RoleCountDTO wicketKeeper = RoleCountDTO.builder().role("Wicket Keeper").count(1).build();
		List<RoleCountDTO> expectedRoleCounts=new ArrayList<RoleCountDTO>();
		expectedRoleCounts.addAll(Arrays.asList(allRounderCount,batmanCount,bowlerCount,wicketKeeper));
	   List<RoleCountDTO> actualRoleCount = iplService.getRoleCountByLabel("MI");
	   Assertions.assertEquals(expectedRoleCounts,actualRoleCount );
	}
	
	@DisplayName("Role Count By Entered InValid Label")
	@Test
	void  testGetRoleCountByInValidLabel() {
		
		Assertions.assertThrows(IllegalArgumentException.class, ()-> iplService.getRoleCountByLabel("Pt"));
	}
	
	@DisplayName("Test Get Team Details data")
	@Test 
	void testGetTeamDetails() {
		TeamDTO t1=	TeamDTO.builder().name("Mumbai Indians").city("Mumbai").coach("Mahila Jayvardhane").home("Wankehede").label("MI").build();
	    TeamDTO t2=	TeamDTO.builder().name("Royal Challengers Bangalore").city("Bengaluru, Karnataka").coach("Simon Katich").home("M. Chinnaswamy Stadium").label("RCB").build();
	    List<TeamDTO> teamList=new ArrayList<TeamDTO>();
	    teamList.addAll(Arrays.asList(t1,t2));
	Assertions.assertEquals(teamList, iplService.getTeamDetails());
		 
	}
	
	
}
