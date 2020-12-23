package com.heraizen.springiplstats.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.heraizen.springiplstats.domain.Player;
import com.heraizen.springiplstats.domain.Team;
import com.heraizen.springiplstats.repo.IplTeamRepo;


@Repository
public class IplTeamDaoImpl implements IplTeamDao {

	@Autowired
	private IplTeamRepo iplTeamRepo;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public List<Team> insertTeams(List<Team> teams) {

		return iplTeamRepo.saveAll(teams);
	}

	@Override
	public Team insertTeam(Team team) {

		return iplTeamRepo.save(team);
	}

	@Override
	public Team updateTeam(Team team) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(team.getId()));
		Update update = new Update();
		update.set("name", team.getName());
		update.set("city", team.getCity());
		update.set("coach", team.getCoach());
		update.set("label", team.getLabel());
		update.set("home", team.getHome());
		update.set("Players", team.getPlayers());	      
	 return	 mongoOperations.findAndModify(query, update, Team.class, "team");
	}

	@Override
	public Team findTeamByLabel(String label) {
		Team team = iplTeamRepo.findByLabel(label);
		return team;
	}

	@Override
	public Optional<Team> findTeamById(String id)  {
		Optional<Team> team = iplTeamRepo.findById(id);
		
		return team;
	}

	@Override
	public void deleteTeam(String id) {
       
		iplTeamRepo.deleteById(id);

	}

	@Override
	public List<Team> findTeams() {
		return iplTeamRepo.findAll();
	}

	@Override
	public void removeAllTeams() {
		iplTeamRepo.deleteAll();

	}

	@Override
	public Player insertPlayer(String id, Player player) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.push("players", player);
	   return mongoOperations.findAndModify(query, update, Player.class, "team");
	  
	}
	

	@Override
	public Player updatePlayer(String id, Player player) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("id").is(id).and("players.name").is("Rohit Sharma (R)"));
//		Update update = new Update();
//		update.set("players", player);
//		System.out.println("Data"+ mongoOperations.findAndModify(query, update, Team.class, "team"));
//	     return mongoOperations.findAndModify(query, update, Player.class, "team");
		return null;
	}

	@Override
	public void removePlayer(String id, String playerName) {
		// TODO Auto-generated method stub

	}

}
