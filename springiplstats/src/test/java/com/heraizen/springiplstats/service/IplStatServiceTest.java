package com.heraizen.springiplstats.service;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.heraizen.springiplstats.dao.IplTeamDao;
import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamLabelNotFoundException;
import com.heraizen.springiplstats.util.JsonReader;

@SpringBootTest
public class IplStatServiceTest {
	@Autowired
	private IplStatService iplStatService;

	@Autowired
	private IplTeamDao iplTeamDao;

	@BeforeEach
	void init() {
		iplTeamDao.removeAllTeams();
		iplTeamDao.insertTeams(JsonReader.getJsonData());
	}
	
	@DisplayName("Test Team Labels")
	@Test
	void Test() throws DataNotFoundException {
		TeamLabelsDTO Teamlabels = iplStatService.getTeamLabels();
		assertThat(Teamlabels.getLabels(), hasSize(8));
	}
	
	@DisplayName("Get player by entered label ")
	@Test
	void TestGetPlayerByEnterValidLabels() throws TeamLabelNotFoundException  {
		List<PlayerDTO> playerList= iplStatService.getPlayerByLabel("MI");
		assertThat(playerList, hasSize(21));

	}

	@DisplayName("Role count by entered label ")
	@Test
	void TestGetRoleCount() throws TeamLabelNotFoundException {
		RoleCountDTO batsman = RoleCountDTO.builder().role("Batsman").count(7).build();
		RoleCountDTO wicketKeeper = RoleCountDTO.builder().role("Wicket Keeper").count(3).build();
		RoleCountDTO allRounder = RoleCountDTO.builder().role("All-Rounder").count(6).build();
		RoleCountDTO bowler = RoleCountDTO.builder().role("Bowler").count(8).build();
		List<RoleCountDTO> expectedRoleCount = new ArrayList<>();
		expectedRoleCount.addAll(Arrays.asList(allRounder,wicketKeeper, bowler, batsman ));
		List<RoleCountDTO> roleCounts = iplStatService.getRoleCountByLabel("MI");
       Assertions.assertEquals(expectedRoleCount, roleCounts);
	}
	
	@DisplayName("Get player role and  label wise ")
	@Test
	void TestGetPlayerRoleAndLabelWise() throws TeamLabelNotFoundException {
		List<PlayerDTO> playerRoleWise = iplStatService.getPlayerByLabelAndRole("MI", "Bowler");
        assertThat(playerRoleWise, hasSize(8));
	}
	
	@DisplayName("Find Team Details ")
	@Test
	void TestTeamDetails() throws DataNotFoundException {
		List<TeamDTO> playerRoleWise = iplStatService.getTeamDetails();
        assertThat(playerRoleWise, hasSize(8));
	}
	
	@DisplayName("Amount spented by each team")
	@Test
	void TestAmountSpentedByEachTeam() throws DataNotFoundException {
		
		TeamAmountDTO spentedAmount = iplStatService.getAmountSpentByTeam("RCB");
        Assertions.assertEquals(7.86E8, spentedAmount.getAmount());
	}
	
	@DisplayName("Amount spented on each role by label")
	@Test
	void TestAmountSpentedByEachRoleByLabel() throws TeamLabelNotFoundException {
		RoleAmountDTO spentedAmountOnAllRounder=RoleAmountDTO.builder().role("All-Rounder").amount(1.84E8).build();
		RoleAmountDTO spentedAmountOnWicketKeeper=RoleAmountDTO.builder().role("Wicket Keeper").amount(2.1E7).build();
		RoleAmountDTO spentedAmountOnBowler=RoleAmountDTO.builder().role("Bowler").amount(2.5E8).build();
		RoleAmountDTO spentedAmountOnBatsMan=RoleAmountDTO.builder().role("Batsman").amount(3.31E8).build();
		List<RoleAmountDTO> expectedSpentedAmountOnEachRole=new ArrayList<RoleAmountDTO>();
		expectedSpentedAmountOnEachRole.addAll(Arrays.asList(spentedAmountOnAllRounder,spentedAmountOnWicketKeeper,spentedAmountOnBowler,spentedAmountOnBatsMan));
	
		 List<RoleAmountDTO> spentedAmountOnEachRole = iplStatService.getAmountSpentOnRoleByLabel("RCB");
       Assertions.assertEquals(expectedSpentedAmountOnEachRole,spentedAmountOnEachRole);
	}
	
	@DisplayName(" Get All Players")
	@Test
	void TestMaxPaidPlayerForEachRole() throws DataNotFoundException {
		
		List<PlayerDTO> allPlayers = iplStatService.getAllPlayers();
       assertThat(allPlayers, hasSize(188));
	}

}
	


