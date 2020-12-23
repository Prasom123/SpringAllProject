package com.heraizen.cj.ipl.service;

import java.util.List;

import com.heraizen.cj.ipl.domain.Players;
import com.heraizen.cj.ipl.domain.Team;
import com.heraizen.cj.ipl.dto.PlayerDTO;
import com.heraizen.cj.ipl.dto.RoleAmountDTO;
import com.heraizen.cj.ipl.dto.RoleCountDTO;
import com.heraizen.cj.ipl.dto.TeamAmountDTO;
import com.heraizen.cj.ipl.dto.TeamDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;

public interface IplNewService {
public TeamLabelsDTO getTeamLabel() ;
public List<PlayerDTO> getPlayerByLabel(String label);
public List<RoleCountDTO> getRoleCountByLabel(String label);
public List<PlayerDTO> getPlayerByLabelAndRole(String label , String role);
public List<TeamDTO>  getTeamDetails();
public TeamAmountDTO getAmountSpentByTeam(String label);
public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label);
public List<PlayerDTO> getMaxPaidPlayerOnEachRole();
public List<PlayerDTO> getAllPlayers();
public List<TeamAmountDTO> getAmountSpentedByEachTeam();
public Team addTeam(Team team) ;
public Team addPlayer(String label, Players player);
}
