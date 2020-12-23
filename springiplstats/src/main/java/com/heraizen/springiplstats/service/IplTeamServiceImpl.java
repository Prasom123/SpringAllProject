package com.heraizen.springiplstats.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.heraizen.springiplstats.dao.IplTeamDao;
import com.heraizen.springiplstats.domain.Player;
import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamAlreadyExistException;

@Service
public class IplTeamServiceImpl implements IplTeamService {

	@Autowired
	private IplTeamDao iplTeamDao;



	private Logger log = LoggerFactory.getLogger(IplTeamServiceImpl.class);

	@Override
	public List<Team> addTeams(List<Team> addTeam) throws DataNotFoundException, TeamAlreadyExistException {
		
		if (addTeam.isEmpty()) {
			throw new DataNotFoundException("Data Not Found ");
		} else {
			 List<Team> teams=iplTeamDao.findTeams();
			 List<Team>  matchedTeam= teams.stream().filter(two -> addTeam.stream()
		              .anyMatch(one -> one.getLabel().equalsIgnoreCase(two.getLabel())))
		              .collect(Collectors.toList());
			 if(matchedTeam.isEmpty() && matchedTeam.size()==0) {
				 return iplTeamDao.insertTeams(addTeam);
			 } 
			   throw new TeamAlreadyExistException("Your teamlist has duplicate team data");
				 
			
		}

	}

	@Override
	public Team addTeam(Team team) throws TeamAlreadyExistException {
		Assert.notNull(team,"Team Can not be null");
		   Team  matchedTeam=iplTeamDao.findTeamByLabel(team.getLabel());
		   if(matchedTeam==null) {
			   log.info("Team added Successfully");
     			return iplTeamDao.insertTeam(team);
		   } 
		   throw new TeamAlreadyExistException("Team already Exist");
		     

	}

	@Override
	public Team updateTeam(Team team) throws DataNotFoundException {
		Assert.notNull(team,"Team Can not be null");
		Team  matchedTeam=iplTeamDao.findTeamByLabel(team.getLabel());
		   if(matchedTeam != null) {
			   log.info("Team updated Successfully");
  			return iplTeamDao.updateTeam(team);
		   } 
		   throw new DataNotFoundException("Data not found");
		
		
	}


	@Override
	public Team findTeamByLabel(String label) throws DataNotFoundException {
		Assert.notNull(label,"Label Can not be null");
		Team team = iplTeamDao.findTeamByLabel(label);
		if (team != null) {
			return team;
		}
		throw new DataNotFoundException("Data not found");

	}

	@Override
	public Team findTeamById(String id) throws DataNotFoundException {
		Assert.notNull(id,"Id Can not be null");
		 Optional<Team> team = iplTeamDao.findTeamById(id);
		if (team.isPresent()) {
			return team.get();
		}
		throw new DataNotFoundException("Data not found");
	}

	@Override
	public boolean deleteTeam(String id) throws DataNotFoundException {
     Assert.notNull(id, "id can not be null");       
      Optional<Team> team = iplTeamDao.findTeamById(id);
     boolean res=false;
     if(team.isPresent()) {
    	   log.info("Team is deleted with id :{}",id);
    	   iplTeamDao.deleteTeam(id);
    	return  res=true;
     } 
      return res;
     
     
	}

	@Override
	public List<Team> getTeams() throws DataNotFoundException {
		   List<Team> teams=iplTeamDao.findTeams();
		   if(!(teams.isEmpty())){
			    return teams;
		   }
		    throw new  DataNotFoundException("Data not found");
		
	}

	@Override
	public void removeAllTeams() {
		iplTeamDao.removeAllTeams();
	}

	@Override
	public Player addPlayer(String id, Player player) {
		// TODO Auto-generated method stub
		return iplTeamDao.insertPlayer(id, player);
	}

	@Override
	public Player updatePlayer(String id, Player player) {
	
		return null;
	}

	@Override
	public void removePlayer(String id, String playerName) {
		// TODO Auto-generated method stub

	}

}
