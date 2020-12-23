package com.heraizen.springiplstats.dao;

import java.util.List;


import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;

public interface IplStatDao {

	public TeamLabelsDTO findTeamLabels();

	public List<PlayerDTO> findPlayerByLabel(String label);

	public List<RoleCountDTO> findRoleCountByLabel(String label);

	public List<PlayerDTO> findPlayerByLabelAndRole(String label, String role);

	public List<TeamDTO> findTeamDetails();

	public TeamAmountDTO findAmountSpentByTeam(String label);

	public List<RoleAmountDTO> findAmountSpentOnRoleByLabel(String label);

	public List<PlayerDTO> findMaxPaidPlayerOnEachRole();

	public List<PlayerDTO> findAllPlayers();
}
