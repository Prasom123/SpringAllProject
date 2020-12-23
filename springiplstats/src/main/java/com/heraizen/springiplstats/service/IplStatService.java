package com.heraizen.springiplstats.service;

import java.util.List;

import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamLabelNotFoundException;

public interface IplStatService {

	public TeamLabelsDTO getTeamLabels() throws DataNotFoundException;

	public List<PlayerDTO> getPlayerByLabel(String label) throws TeamLabelNotFoundException;

	public List<RoleCountDTO> getRoleCountByLabel(String label) throws TeamLabelNotFoundException;

	public List<PlayerDTO> getPlayerByLabelAndRole(String label, String role) throws TeamLabelNotFoundException;

	public List<TeamDTO> getTeamDetails() throws DataNotFoundException;

	public TeamAmountDTO getAmountSpentByTeam(String label) throws DataNotFoundException;

	public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label) throws TeamLabelNotFoundException;

	public List<PlayerDTO> getMaxPaidPlayerOnEachRole();

	public List<PlayerDTO> getAllPlayers() throws DataNotFoundException;
}
