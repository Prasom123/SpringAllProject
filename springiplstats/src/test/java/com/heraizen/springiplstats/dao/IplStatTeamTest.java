package com.heraizen.springiplstats.dao;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;
import com.heraizen.springiplstats.util.JsonReader;

@SpringBootTest
public class IplStatTeamTest {

	@Autowired
	private IplStatDao iplStatDao;

	@Autowired
	private IplTeamDao iplTeamDao;

	@BeforeEach
	void init() {
		iplTeamDao.removeAllTeams();
		iplTeamDao.insertTeams(JsonReader.getJsonData());
	}

	@DisplayName("Test Get Team Labels")
	@Test
	void TestGetTeamLabels() {
		TeamLabelsDTO Teamlabels = iplStatDao.findTeamLabels();
		assertThat(Teamlabels.getLabels(), hasSize(8));

	}

	@DisplayName("Get player by entered label ")
	@Test
	void TestGetPlayerByEnterValidLabels() {
		List<PlayerDTO> playerList = iplStatDao.findPlayerByLabel("RCB");
		assertThat(playerList, hasSize(21));

	}

	@DisplayName("Role count by entered label ")
	@Test
	void TestGetRoleCount() {
		RoleCountDTO batsman = RoleCountDTO.builder().role("Batsman").count(7).build();
		RoleCountDTO wicketKeeper = RoleCountDTO.builder().role("Wicket Keeper").count(3).build();
		RoleCountDTO allRounder = RoleCountDTO.builder().role("All-Rounder").count(6).build();
		RoleCountDTO bowler = RoleCountDTO.builder().role("Bowler").count(8).build();
		List<RoleCountDTO> expectedRoleCount = new ArrayList<>();
		expectedRoleCount.addAll(Arrays.asList(allRounder, wicketKeeper, bowler, batsman));

		List<RoleCountDTO> roleCounts = iplStatDao.findRoleCountByLabel("MI");
       Assertions.assertEquals(expectedRoleCount, roleCounts);
	}
	
	@DisplayName("Get player role and  label wise ")
	@Test
	void TestGetPlayerRoleAndLabelWise() {
		List<PlayerDTO> playerRoleWise = iplStatDao.findPlayerByLabelAndRole("MI", "Bowler");
        assertThat(playerRoleWise, hasSize(8));
	}
	
	@DisplayName("Find Team Details ")
	@Test
	void TestTeamDetails() {
		List<TeamDTO> playerRoleWise = iplStatDao.findTeamDetails();
        assertThat(playerRoleWise, hasSize(8));
	}
	
	@DisplayName("Amount spented by each team")
	@Test
	void TestAmountSpentedByEachTeam() {
		
		TeamAmountDTO spentedAmount = iplStatDao.findAmountSpentByTeam("RCB");
        Assertions.assertEquals(7.86E8, spentedAmount.getAmount());
	}
	
	@DisplayName("Amount spented on each role by label")
	@Test
	void TestAmountSpentedByEachRoleByLabel() {
		RoleAmountDTO spentedAmountOnAllRounder=RoleAmountDTO.builder().role("All-Rounder").amount(1.84E8).build();
		RoleAmountDTO spentedAmountOnWicketKeeper=RoleAmountDTO.builder().role("Wicket Keeper").amount(2.1E7).build();
		RoleAmountDTO spentedAmountOnBowler=RoleAmountDTO.builder().role("Bowler").amount(2.5E8).build();
		RoleAmountDTO spentedAmountOnBatsMan=RoleAmountDTO.builder().role("Batsman").amount(3.31E8).build();
		List<RoleAmountDTO> expectedSpentedAmountOnEachRole=new ArrayList<RoleAmountDTO>();
		expectedSpentedAmountOnEachRole.addAll(Arrays.asList(spentedAmountOnAllRounder,spentedAmountOnWicketKeeper,spentedAmountOnBowler,spentedAmountOnBatsMan));
		 List<RoleAmountDTO> spentedAmountOnEachRole = iplStatDao.findAmountSpentOnRoleByLabel("RCB");
       Assertions.assertEquals(expectedSpentedAmountOnEachRole,spentedAmountOnEachRole);
	}
	
	@DisplayName(" Get All Players")
	@Test
	void TestMaxPaidPlayerForEachRole() {
		
		List<PlayerDTO> allPlayers = iplStatDao.findAllPlayers();
       assertThat(allPlayers, hasSize(188));
	}

}
