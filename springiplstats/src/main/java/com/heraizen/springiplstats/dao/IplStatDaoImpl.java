package com.heraizen.springiplstats.dao;

import java.util.List;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.heraizen.springiplstats.dto.PlayerDTO;
import com.heraizen.springiplstats.dto.RoleAmountDTO;
import com.heraizen.springiplstats.dto.RoleCountDTO;
import com.heraizen.springiplstats.dto.TeamAmountDTO;
import com.heraizen.springiplstats.dto.TeamDTO;
import com.heraizen.springiplstats.dto.TeamLabelsDTO;

@Repository
public class IplStatDaoImpl implements IplStatDao {

	private Logger log = LoggerFactory.getLogger(IplStatDaoImpl.class);

	@Autowired
	private MongoOperations monogoOperations;

	@Override
	public TeamLabelsDTO findTeamLabels() {
		Aggregation query = newAggregation(group("null").addToSet("label").as("labels"), project().andExclude("_id"));
		AggregationResults<TeamLabelsDTO> result = monogoOperations.aggregate(query, "team", TeamLabelsDTO.class);
		log.debug("Generated query :{}", query);
		return result.getUniqueMappedResult();

	}

	@Override
	public List<PlayerDTO> findPlayerByLabel(String label) {
		MatchOperation match = match(Criteria.where("label").is(label));
		UnwindOperation unwind = unwind("players");
		ProjectionOperation project = project().and("players.name").as("name").and("players.role").as("role")
				.and("players.price").as("price").andExclude("_id");

		Aggregation query = newAggregation(match, unwind, project);
		AggregationResults<PlayerDTO> result = monogoOperations.aggregate(query, "team", PlayerDTO.class);

		List<PlayerDTO> playerlist = result.getMappedResults();
		log.info("Total {} players found for the given label: {}", playerlist.size(), label);
		return playerlist;
	}

	@Override
	public List<RoleCountDTO> findRoleCountByLabel(String label) {
		MatchOperation match = match(Criteria.where("label").is(label));
		UnwindOperation unwind = unwind("players");
		GroupOperation group = group("players.role").count().as("roleCount");
		ProjectionOperation project = project().and("_id").as("role").and("roleCount").as("count").andExclude("_id");
		Aggregation query = newAggregation(match, unwind, group, project);
		AggregationResults<RoleCountDTO> result = monogoOperations.aggregate(query, "team", RoleCountDTO.class);

		List<RoleCountDTO> roleCounts = result.getMappedResults();
		log.info("Total {} role count for the given label: ", roleCounts);
		return roleCounts;
	}

	@Override
	public List<PlayerDTO> findPlayerByLabelAndRole(String label, String role) {
		MatchOperation matchLabel = match(Criteria.where("label").is(label));
		UnwindOperation unwind = unwind("players");
		MatchOperation matchRole = match(Criteria.where("players.role").is(role));
		ProjectionOperation project = project().and("players.name").as("name").and("players.role").as("role")
				.and("players.price").as("price").andExclude("_id");
		Aggregation query = newAggregation(matchLabel, unwind, matchRole, project);

		AggregationResults<PlayerDTO> result = monogoOperations.aggregate(query, "team", PlayerDTO.class);

		List<PlayerDTO> playersRoleWise = result.getMappedResults();
		log.info("Total {} Player  for the given label and role: ", playersRoleWise.size());
		return playersRoleWise;
	}

	@Override
	public List<TeamDTO> findTeamDetails() {
		ProjectionOperation project = project().andExclude("_id", "players");
		Aggregation query = newAggregation(project);

		AggregationResults<TeamDTO> result = monogoOperations.aggregate(query, "team", TeamDTO.class);

		List<TeamDTO> allTeamDetails = result.getMappedResults();
		log.info("Total {} team details: ", allTeamDetails.size());
		return allTeamDetails;

	}

	@Override
	public TeamAmountDTO findAmountSpentByTeam(String label) {
		MatchOperation matchLabel = match(Criteria.where("label").is(label));
		UnwindOperation unwind = unwind("players");
		GroupOperation group = group("label").sum("players.price").as("totalAmount");
		ProjectionOperation project = project().and("_id").as("label").and("totalAmount").as("amount")
				.andExclude("_id");
		Aggregation query = newAggregation(matchLabel, unwind, group, project);
		log.debug("Generated query" + query);
		AggregationResults<TeamAmountDTO> result = monogoOperations.aggregate(query, "team", TeamAmountDTO.class);

		TeamAmountDTO spentedAmount = result.getUniqueMappedResult();
		log.info("{} Total amount spented {} : ", spentedAmount.getLabel(), spentedAmount);
		return spentedAmount;
	}

	@Override
	public List<RoleAmountDTO> findAmountSpentOnRoleByLabel(String label) {
		MatchOperation matchLabel = match(Criteria.where("label").is(label));
		UnwindOperation unwind = unwind("players");
		GroupOperation group = group("players.role").sum("players.price").as("totalAmount");
		ProjectionOperation project = project().and("_id").as("role").and("totalAmount").as("amount")
				.andExclude("_id");
		Aggregation query = newAggregation(matchLabel, unwind, group, project);
		log.debug("Generated query" + query);
		AggregationResults<RoleAmountDTO> result = monogoOperations.aggregate(query, "team", RoleAmountDTO.class);

		List<RoleAmountDTO> spentedAmountOnEachRole = result.getMappedResults();
		
		log.info("Total amount spented on each role {} : ", spentedAmountOnEachRole );
		return spentedAmountOnEachRole;
	}

	@Override
	public List<PlayerDTO> findMaxPaidPlayerOnEachRole() {

		UnwindOperation unwind = unwind("players");
		GroupOperation group = group("players.role").max("players.price").as("maxPrice");
		ProjectionOperation project = project().and("_id").as("role").and("maxPrice").as("price")
				.andExclude("_id");
		Aggregation query = newAggregation( unwind, group, project);
		log.debug("Generated querys" + query);
		AggregationResults<PlayerDTO> result = monogoOperations.aggregate(query, "team", PlayerDTO.class);

		List<PlayerDTO> maxPaidPlayerForEachRole = result.getMappedResults();
		
		log.info("Max paid player for each role : ", maxPaidPlayerForEachRole );
		return maxPaidPlayerForEachRole;

	}

	@Override
	public List<PlayerDTO> findAllPlayers() {
		UnwindOperation unwind = unwind("players");
		ProjectionOperation project = project().and("players.name").as("name").and("players.role").as("role").
				and("players.price").as("price")
				.andExclude("_id");
		Aggregation query = newAggregation( unwind, project);
		log.debug("Generated querys" + query);
		AggregationResults<PlayerDTO> result = monogoOperations.aggregate(query, "team", PlayerDTO.class);

		List<PlayerDTO> allPlayers = result.getMappedResults();
		
		log.info("Total {} players : ", allPlayers.size() );
		return allPlayers;
	}

}
