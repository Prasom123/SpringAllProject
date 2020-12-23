package com.heraizen.springiplstats.service;

import java.util.List;

import com.heraizen.springiplstats.domain.Player;
import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamAlreadyExistException;

public interface IplTeamService {
	public List<Team> addTeams(List<Team> addTeam) throws DataNotFoundException, TeamAlreadyExistException;

	public Team addTeam(Team team) throws TeamAlreadyExistException;

	public Team updateTeam(Team team) throws DataNotFoundException;

	public Team findTeamByLabel(String label) throws DataNotFoundException;

	public Team findTeamById(String id) throws DataNotFoundException;

	public boolean deleteTeam(String id) throws DataNotFoundException;

	public List<Team> getTeams() throws DataNotFoundException;

	public void removeAllTeams();

	public Player addPlayer(String id, Player player);

	public Player updatePlayer(String id, Player player);

	public void removePlayer(String id, String playerName);
}
