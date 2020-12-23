package com.heraizen.springiplstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.heraizen.springiplstats.dao.IplTeamDao;
import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamAlreadyExistException;
import com.heraizen.springiplstats.util.JsonReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class IplTeamServiceTest {
    
	@Autowired
	private IplTeamService iplTeamService;
	


	@Autowired
	private IplTeamDao iplTeamDao;

	@BeforeEach
	void init() {
		iplTeamDao.removeAllTeams();
		iplTeamDao.insertTeams(JsonReader.getJsonData());
	}
	
	@DisplayName("Add New Team")
	@Test
	void TestAddNewTeam() throws TeamAlreadyExistException {
	  Team	team=Team.builder().name("Patna Riders").label("PPR").city("Patna").coach("Badal").home("Patna").build();
	  	Team actual=iplTeamService.addTeam(team);
	  	System.out.println("actual "+actual);
	  	System.out.println(iplTeamDao.findTeams().size());
	  	Assertions.assertEquals(team, actual);
	    
	}
	@DisplayName("Add Existing  Team")
	@Test
	void Test() throws TeamAlreadyExistException {
	  Team	team=Team.builder().name("Patna Riders").label("MI").city("Patna").coach("Badal").home("Patna").build();
	  
	  	Assertions.assertThrows(TeamAlreadyExistException.class, ()-> iplTeamService.addTeam(team));
	    
	}
	
	
	@DisplayName("Find team by invalid label")
	@Test
	void TestfindTeamByInValidLabel() throws DataNotFoundException {  
		
	  	Assertions.assertThrows(DataNotFoundException.class, ()-> iplTeamService.findTeamByLabel("PI"));
	    
	}
	@DisplayName("Find Team by valid label")
	@Test
	void TestfindTeamByValidLabel() throws  DataNotFoundException {
	  
	  	Assertions.assertEquals("Mumbai, Maharashtra", iplTeamService.findTeamByLabel("MI").getCity());
	    
	}
	@DisplayName("Get All Teams")
	@Test
	void TestGetAllTeams() throws  DataNotFoundException {
	  
	  	 assertThat(iplTeamService.getTeams(),hasSize(8)); 
	    
	}
	
}
