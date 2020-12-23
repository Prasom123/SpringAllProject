package com.heraizen.springcorebrand.service;

import java.util.List;

import com.heraizen.springcorebrand.domain.Team;
import com.heraizen.springcorebrand.dto.PlayerDTO;
import com.heraizen.springcorebrand.dto.RoleAmountDTO;
import com.heraizen.springcorebrand.dto.RoleCountDTO;
import com.heraizen.springcorebrand.dto.TeamAmountDTO;
import com.heraizen.springcorebrand.dto.TeamDTO;
import com.heraizen.springcorebrand.dto.TeamLabelsDTO;



public interface IplService {
	  public TeamLabelsDTO getTeamLabels();
	  public List<PlayerDTO> getPlayerByLabel(String label);
	  public List<RoleCountDTO> getRoleCountByLabel(String label);
	  public List<PlayerDTO> getPlayerByLabelAndRole(String label , String role);
	  public List<TeamDTO>  getTeamDetails();
	  public TeamAmountDTO getAmountSpentByTeam(String label);
	  public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label);
	  public List<PlayerDTO> getMaxPaidPlayerOnEachRole();
	  public List<PlayerDTO> getAllPlayers();
	  public void addTeam(List<Team> teams);
	  public void removeAllTeam();
	  
}
