package com.heraizen.cj.ipl.service;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Aggregates.unwind;
import static com.mongodb.client.model.Aggregates.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.BsonNull;
import org.bson.BsonValue;
import org.bson.conversions.Bson;

import com.heraizen.cj.ipl.domain.Players;
import com.heraizen.cj.ipl.domain.Team;
import com.heraizen.cj.ipl.dto.PlayerDTO;
import com.heraizen.cj.ipl.dto.RoleAmountDTO;
import com.heraizen.cj.ipl.dto.RoleCountDTO;
import com.heraizen.cj.ipl.dto.TeamAmountDTO;
import com.heraizen.cj.ipl.dto.TeamDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;
import com.heraizen.cj.ipl.util.IplServiceDatabaseConnection;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.result.InsertOneResult;

public class IplNewServiceImpl implements IplNewService {
	private static IplNewServiceImpl iplNewServiceImpl = null;
	private IplServiceDatabaseConnection iplServiceDBConnection = null;

	private IplNewServiceImpl() {
		iplServiceDBConnection = IplServiceDatabaseConnection.getInstance();
	}

	public static IplNewServiceImpl getInstance() {
		if (iplNewServiceImpl == null) {
			synchronized (IplServiceDatabaseConnection.class) {
				if (iplNewServiceImpl == null) {
					iplNewServiceImpl = new IplNewServiceImpl();
				}
			}
		}
		return iplNewServiceImpl;
	}

	@Override
	public TeamLabelsDTO getTeamLabel() {
		TeamLabelsDTO teamLabelDTO = null;
		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");
			MongoCollection<TeamLabelsDTO> result = database.getCollection("ipldata", TeamLabelsDTO.class);

			AggregateIterable<TeamLabelsDTO> resObj = result
					.aggregate(Arrays.asList(group(new BsonNull(), push("labels", "$label")), project(excludeId())));
			teamLabelDTO = resObj.first();
		}
		return teamLabelDTO;
	}

	@Override
	public List<TeamAmountDTO> getAmountSpentedByEachTeam() {
		List<TeamAmountDTO> listTeamAmount = new ArrayList<>();
		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");
			MongoCollection<TeamAmountDTO> result = database.getCollection("ipldata", TeamAmountDTO.class);

			AggregateIterable<TeamAmountDTO> resObj = result
					.aggregate(Arrays.asList(unwind("$players"), group("$label", sum("amount", "$players.price")),
							project(fields(excludeId(), include("amount"), computed("label", "$_id")))));
			listTeamAmount = resObj.into(listTeamAmount);
			System.out.println(listTeamAmount);
		}
		return listTeamAmount;
	}

	@Override
	public Team addTeam(Team team) {

		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");

			MongoCollection<Team> result = database.getCollection("ipldata", Team.class);

			result.insertOne(team);

		}

		return team;
	}

	@Override
	public Team addPlayer(String label, Players player) {
		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");

			MongoCollection<Team> result = database.getCollection("ipldata", Team.class);

			FindIterable<Team> res = result.find(Filters.eq("label", "CSK"));
			System.out.println(res);

		}
		return null;
	}

	@Override
	public List<PlayerDTO> getPlayerByLabel(String label) {
		List<PlayerDTO> players=new ArrayList<PlayerDTO>();
		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");

			MongoCollection<Players> collection = database.getCollection("ipldata", Players.class);

			AggregateIterable<Players> pl = collection.aggregate(Arrays.asList( match(Filters.eq("label",label )),unwind("$players"), project(fields(excludeId(),computed("name", "$players.name" ) ,
					computed("role", "$players.role" ), computed("price", "$players.price" ) ))));
		    System.out.println(pl.first());
		    pl.forEach(player ->{
		    	player.getName();
		    	players.add(PlayerDTO.builder().name(player.getName()).role(player.getRole()).price(player.getPrice()).build());
		    });

		}
	   
		return players;
	}

	@Override
	public List<RoleCountDTO> getRoleCountByLabel(String label) {
		List<PlayerDTO> players=new ArrayList<PlayerDTO>();
		try (MongoClient client = iplServiceDBConnection.getMongoClient()) {
			MongoDatabase database = client.getDatabase("teamsinfo");

			MongoCollection<RoleCountDTO> collection = database.getCollection("ipldata", RoleCountDTO.class);

			AggregateIterable<RoleCountDTO> pl = collection.aggregate(Arrays.asList( match(Filters.eq("label",label ))
					,unwind("$players"),group("&players.role", sum("rolecount", 1)), project(fields(excludeId(),computed("role", "$players.role" ), computed("count", "$rolecount" ) ))));
		    System.out.println(pl.first());
		    

		}
		return null;
	}

	@Override
	public List<PlayerDTO> getPlayerByLabelAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamDTO> getTeamDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamAmountDTO getAmountSpentByTeam(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> getMaxPaidPlayerOnEachRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

}
