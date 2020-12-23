package com.heraizen.springiplstats.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heraizen.springiplstats.domain.Player;
import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamAlreadyExistException;
import com.heraizen.springiplstats.service.IplTeamService;

@RestController
@RequestMapping("/api/v1/team/")
public class IplTeamController {
	@Autowired
	private IplTeamService iplTeamService;
	
	@PostMapping
	public Team addTeam(@RequestBody Team team) throws TeamAlreadyExistException {
		return iplTeamService.addTeam(team);
	}
	@PostMapping("addTeams")
	public List<Team> addTeam(@RequestBody List<Team> addteams) throws TeamAlreadyExistException, DataNotFoundException {
		return iplTeamService.addTeams(addteams);
	}
	
	@PutMapping("updateTeam")
	public Team updateTeam(@RequestBody Team team) throws DataNotFoundException {
		Team updatedTeam = iplTeamService.updateTeam(team); // Invoke method the update team
		return updatedTeam;
	}
	@PutMapping("addPlayer/{id}")
	public Player addPlayer(@PathVariable("id")String teamId ,@RequestBody Player player) throws DataNotFoundException {
		 
		return   iplTeamService.addPlayer(teamId, player);
	}
	
	@GetMapping("bylabel/{label}")
	public Team findTeamByLabel(@PathVariable("label") String label) throws DataNotFoundException {
		return iplTeamService.findTeamByLabel(label);
	}
	
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteTeam(@PathVariable("id")String teamId) throws DataNotFoundException{
		
		boolean res = iplTeamService.deleteTeam(teamId);
		ResponseMessage  responseMessage=null;		
		if(res) {
			responseMessage = ResponseMessage.builder().message("Deleted successfully").build();
		}else {
			  responseMessage = ResponseMessage.builder().message("Not delted successfully").build();
		}
		return ResponseEntity.ok().body(responseMessage);
	
	}
}
