package com.heraizen.springiplstats.dao;

import java.util.List;
import java.util.Optional;

import com.heraizen.springiplstats.domain.Player;
import com.heraizen.springiplstats.domain.Team;


public interface IplTeamDao {
	public List<Team> insertTeams(List<Team> team);

	public Team insertTeam(Team team) ;

	public Team updateTeam(Team team);

	public Team findTeamByLabel(String label);

	public Optional<Team> findTeamById(String id) ;

	public void deleteTeam(String id);

	public List<Team> findTeams();

	public void removeAllTeams();

	public Player insertPlayer(String id, Player player);

	public Player updatePlayer(String id, Player player);

	public void removePlayer(String id, String playerName);
}
