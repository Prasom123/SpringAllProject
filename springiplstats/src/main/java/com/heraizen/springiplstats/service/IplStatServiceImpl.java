package com.heraizen.springiplstats.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.heraizen.springiplstats.dao.IplStatDao;
import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamLabelNotFoundException;

@Service
public class IplStatServiceImpl implements IplStatService {

	private static final Logger log = LoggerFactory.getLogger(IplStatServiceImpl.class);

	@Autowired
	private IplStatDao iplStatDao;

	@Override
	public TeamLabelsDTO getTeamLabels() throws DataNotFoundException {
		TeamLabelsDTO teamLabels = iplStatDao.findTeamLabels();
		if (teamLabels != null) {
			log.info("Total {} team labels found ", teamLabels.getLabels().size());
			return teamLabels;
		}
		log.info("There no teams added yet, and labels return as null");
		throw new DataNotFoundException("There are no labels in data source");
	}

	@Override
	public List<PlayerDTO> getPlayerByLabel(String label) throws TeamLabelNotFoundException {
		Assert.notNull(label, "Label can't be empty or null");

		List<PlayerDTO> players = iplStatDao.findPlayerByLabel(label);
		if (players.isEmpty()) {
			throw new TeamLabelNotFoundException("Team Label not found");
		} else {
			log.info("For given team: {} has total {} player ", label, players.size());
			return players;
		}

	}

	@Override
	public List<RoleCountDTO> getRoleCountByLabel(String label) throws TeamLabelNotFoundException {
		List<RoleCountDTO> roleCounts = iplStatDao.findRoleCountByLabel(label);
		if (roleCounts.isEmpty()) {
			throw new TeamLabelNotFoundException("Team Label Not Found");
		} else {
			log.info("For given team:{} Role count {} ", label, roleCounts);
			return roleCounts;
		}

	}

	@Override
	public List<PlayerDTO> getPlayerByLabelAndRole(String label, String role) throws TeamLabelNotFoundException {
		List<PlayerDTO> players = iplStatDao.findPlayerByLabelAndRole(label, role);
		if (players.isEmpty()) {
			throw new TeamLabelNotFoundException("Team Label and role no found");
		} else {
			log.info("For given team {} and Role {} wise players {} ", label, role, players);
			return players;
		}

	}

	@Override
	public List<TeamDTO> getTeamDetails() throws DataNotFoundException {
		 List<TeamDTO> teamDetails = iplStatDao.findTeamDetails();
		if (teamDetails.isEmpty()) {
			throw new DataNotFoundException("Data not found");
		} else {
			log.info(" All Team Details {}" , teamDetails);
			return teamDetails;
		}
	}

	@Override
	public TeamAmountDTO getAmountSpentByTeam(String label) throws DataNotFoundException {
		  TeamAmountDTO spentedAmount = iplStatDao.findAmountSpentByTeam(label);
		  if(spentedAmount !=null) {
			  log.info("{} team spented amount {}" ,label,spentedAmount);
			  return  spentedAmount;
		  } else {
			   throw new DataNotFoundException("Data not found");
		  }
	}

	@Override
	public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label) throws TeamLabelNotFoundException {
		
		 List<RoleAmountDTO> spentedAmountOnEachRole = iplStatDao.findAmountSpentOnRoleByLabel(label);
		  if(!(spentedAmountOnEachRole.isEmpty())) {
			  log.info("{} team spented amount on each role {}" ,label,spentedAmountOnEachRole);
			  return  spentedAmountOnEachRole;
		  } else {
			   throw new TeamLabelNotFoundException("Team label not found");
		  }
		
	}

	@Override
	public List<PlayerDTO> getMaxPaidPlayerOnEachRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> getAllPlayers() throws DataNotFoundException {
		List<PlayerDTO> allPlayers = iplStatDao.findAllPlayers();
		  if(!(allPlayers.isEmpty())) {
			  log.info("All Players Details {}" , allPlayers);
			  return  allPlayers;
		  } else {
			   throw new DataNotFoundException("Data not found ");
		  }
	}

}
