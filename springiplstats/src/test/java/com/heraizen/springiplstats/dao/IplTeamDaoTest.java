package com.heraizen.springiplstats.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.util.JsonReader;

@SpringBootTest
public class IplTeamDaoTest {

	@Autowired
	private IplTeamDao iplTeamDao;

	@BeforeEach
	void init() {
		iplTeamDao.removeAllTeams();
		iplTeamDao.insertTeams(JsonReader.getJsonData());
	}

	@DisplayName("Get Team By label")
	@Test
	void TestTeamByLabel() {
		Team team = iplTeamDao.findTeamByLabel("MI");
		Assertions.assertEquals("MI", team.getLabel());
	}

	@DisplayName("Get Team By ID")
	@Test
	void TestTeamById() {
		
	     List<Team> team = iplTeamDao.findTeams();
	   	       
	     Assertions.assertEquals("MI", team.get(0).getLabel());
	}
	@DisplayName("Get All Teams")
	@Test
	void TestGetAllTeams() {
	     Assertions.assertEquals(iplTeamDao.findTeams().size(),8);
	}
	@DisplayName("Delete Team")
	@Test
	void TestDeleteTeam() {
		       iplTeamDao.deleteTeam(iplTeamDao.findTeams().get(0).getId());
	     Assertions.assertEquals(iplTeamDao.findTeams().size(),7);
	}

	
}
