package com.heraizen.cj.ipl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.heraizen.cj.ipl.domain.Players;
import com.heraizen.cj.ipl.domain.Team;
import com.heraizen.cj.ipl.dto.PlayerDTO;
import com.heraizen.cj.ipl.dto.RoleAmountDTO;
import com.heraizen.cj.ipl.dto.RoleCountDTO;
import com.heraizen.cj.ipl.dto.TeamAmountDTO;
import com.heraizen.cj.ipl.dto.TeamDTO;
import com.heraizen.cj.ipl.dto.TeamLabelsDTO;
import com.heraizen.cj.ipl.util.FileType;
import com.heraizen.cj.ipl.util.ReaderUtil;

public class IplServiceImpl implements IplService {
	private static transient IplServiceImpl iplServiceImplObj = null;
	private List<Team> teamList = new ArrayList<>();

	private IplServiceImpl() {
	//	teamList = ReaderUtil.getTypeData();
		teamList=ReaderUtil.getTypeData(FileType.Yaml);
		System.out.println(teamList.size());
	}

	public static IplServiceImpl getInstance() {
		if (iplServiceImplObj == null) {
			synchronized (IplServiceImpl.class) {
				if (iplServiceImplObj == null) {
					iplServiceImplObj = new IplServiceImpl();
				}
			}
		}
		return iplServiceImplObj;
	}

	@Override
	public TeamLabelsDTO getTeamLabels() {
		List<String> labels = teamList.stream().map(Team::getLabel).collect(Collectors.toList());
		TeamLabelsDTO teamLabels = TeamLabelsDTO.builder().labels(labels).build();
		return teamLabels;
	}

	@Override
	public List<PlayerDTO> getPlayerByLabel(String label) {
		List<PlayerDTO> playerByLabel = new ArrayList<>();

		teamList.forEach(t -> {
			if (t.getLabel().equalsIgnoreCase(label)) {
				t.getPlayers().forEach(p -> {
					PlayerDTO playerDTO = PlayerDTO.builder().name(p.getName()).price(p.getPrice()).role(p.getRole())
							.build();
					playerByLabel.add(playerDTO);
				});
			}
		});

		return playerByLabel;
	}

	@Override
	public List<RoleCountDTO> getRoleCountByLabel(String label) {
		List<PlayerDTO> playerByLabel = new ArrayList<>();
		teamList.forEach(t -> {
			if (t.getLabel().equalsIgnoreCase(label)) {
				t.getPlayers().forEach(p -> {
					PlayerDTO playerDTO = PlayerDTO.builder().name(p.getName()).price(p.getPrice()).role(p.getRole())
							.build();
					playerByLabel.add(playerDTO);
				});
			}
		});
		List<RoleCountDTO> roleCountDTO = new ArrayList<RoleCountDTO>();
		if(playerByLabel.size()!=0) {
		
		long batsman = playerByLabel.stream().filter(p -> p.getRole().equalsIgnoreCase("Batsman")).count();
		long wicketKeeper = playerByLabel.stream().filter(p -> p.getRole().equalsIgnoreCase("Wicket Keeper")).count();
		long allRounder = playerByLabel.stream().filter(p -> p.getRole().equalsIgnoreCase("All-Rounder")).count();
		long bowler = playerByLabel.stream().filter(p -> p.getRole().equalsIgnoreCase("Bowler")).count();

		roleCountDTO.add(RoleCountDTO.builder().role("All-Rounder").count(allRounder).build());
		roleCountDTO.add(RoleCountDTO.builder().role("Batsman").count(batsman).build());
		roleCountDTO.add(RoleCountDTO.builder().role("Bowler").count(bowler).build());
		roleCountDTO.add(RoleCountDTO.builder().role("Wicket Keeper").count(wicketKeeper).build());
		}
		return roleCountDTO;
	}

	@Override
	public List<PlayerDTO> getPlayerByLabelAndRole(String label, String role) {
		List<List<Players>> listPlayers = teamList.stream().filter(t -> t.getLabel().equalsIgnoreCase(label))
				.map(Team::getPlayers).collect(Collectors.toList());
        List<PlayerDTO> allPlayers=new ArrayList<PlayerDTO>();
        for (List<Players> players : listPlayers) {
			  for(Players player:players) {
				  if(player.getRole().equalsIgnoreCase(role)) {
					  allPlayers.add(PlayerDTO.builder().name(player.getName()).price(player.getPrice()).role(player.getRole()).build());
				  } 
			  }
		}

		return allPlayers;
	}

	@Override
	public List<TeamDTO> getTeamDetails() {
		List<TeamDTO> teamDetails = new ArrayList<TeamDTO>();
		teamList.forEach(team -> {
			teamDetails.add(TeamDTO.builder().name(team.getName()).coach(team.getCoach()).home(team.getHome())
					.label(team.getLabel()).build());
		});
		return teamDetails;
	}

	@Override
	public TeamAmountDTO getAmountSpentByTeam(String label) {
		List<List<Players>> listPlayers = teamList.stream().filter(t -> t.getLabel().equalsIgnoreCase(label))
				.map(Team::getPlayers).collect(Collectors.toList());
		TeamAmountDTO amountSpentByEachTeam = null;
		for (List<Players> players : listPlayers) {
			double amountSpentByTeam = players.stream().mapToDouble(p -> p.getPrice()).sum();
			amountSpentByEachTeam = TeamAmountDTO.builder().label(label).amount(amountSpentByTeam).build();
		}
		return amountSpentByEachTeam;
	}

	@Override
	public List<RoleAmountDTO> getAmountSpentOnRoleByLabel(String label) {
		List<List<Players>> listPlayers = teamList.stream().filter(t -> t.getLabel().equalsIgnoreCase(label))
				.map(Team::getPlayers).collect(Collectors.toList());
		List<RoleAmountDTO> amountSpentForEachRole = new ArrayList<>();
		for (List<Players> players : listPlayers) {
			double amountSpentOnBatsMan = players.stream().filter(p -> p.getRole().equalsIgnoreCase("Batsman"))
					.mapToDouble(p -> p.getPrice()).sum();
			double amountSpentOnBowler = players.stream().filter(p -> p.getRole().equalsIgnoreCase("Bowler"))
					.mapToDouble(p -> p.getPrice()).sum();
			double amountSpentOnAllRounder = players.stream().filter(p -> p.getRole().equalsIgnoreCase("All-Rounder"))
					.mapToDouble(p -> p.getPrice()).sum();
			double amountSpentOnWicketKeeper = players.stream()
					.filter(p -> p.getRole().equalsIgnoreCase("Wicket Keeper")).mapToDouble(p -> p.getPrice()).sum();
			amountSpentForEachRole.add(RoleAmountDTO.builder().role("Batsman").amount(amountSpentOnBatsMan).build());
			amountSpentForEachRole.add(RoleAmountDTO.builder().role("Bowler").amount(amountSpentOnBowler).build());
			amountSpentForEachRole
					.add(RoleAmountDTO.builder().role("All-Rounder").amount(amountSpentOnAllRounder).build());
			amountSpentForEachRole
					.add(RoleAmountDTO.builder().role("Wicket Keeper").amount(amountSpentOnWicketKeeper).build());

		}

		return amountSpentForEachRole;
	}

	@Override
	public List<PlayerDTO> getMaxPaidPlayerOnEachRole() {
		List<List<Players>> listPlayers = teamList.stream().map(Team::getPlayers).collect(Collectors.toList());
		List<Players> allPlayers = new ArrayList<>();
		for (List<Players> players : listPlayers) {
			allPlayers.addAll(players);

		}
		List<PlayerDTO> maxPaidPlayersOnEachRole = new ArrayList<>();
		Players maxPaidForBatsman = allPlayers.stream().filter(p -> p.getRole().equalsIgnoreCase("Batsman"))
				.max(Comparator.comparing(Players::getPrice)).get();
		Players maxPaidForBowler = allPlayers.stream().filter(p -> p.getRole().equalsIgnoreCase("Bowler"))
				.max(Comparator.comparing(Players::getPrice)).get();
		Players maxPaidForAllRounder = allPlayers.stream().filter(p -> p.getRole().equalsIgnoreCase("All-Rounder"))
				.max(Comparator.comparing(Players::getPrice)).get();
		Players maxPaidForWicketKeeper = allPlayers.stream().filter(p -> p.getRole().equalsIgnoreCase("Wicket Keeper"))
				.max(Comparator.comparing(Players::getPrice)).get();
		maxPaidPlayersOnEachRole.add(PlayerDTO.builder().name(maxPaidForBatsman.getName())
				.role(maxPaidForBatsman.getRole()).price(maxPaidForBatsman.getPrice()).build());
		maxPaidPlayersOnEachRole.add(PlayerDTO.builder().name(maxPaidForBowler.getName())
				.role(maxPaidForBowler.getRole()).price(maxPaidForBowler.getPrice()).build());
		maxPaidPlayersOnEachRole.add(PlayerDTO.builder().name(maxPaidForAllRounder.getName())
				.role(maxPaidForAllRounder.getRole()).price(maxPaidForAllRounder.getPrice()).build());
		maxPaidPlayersOnEachRole.add(PlayerDTO.builder().name(maxPaidForBatsman.getName())
				.role(maxPaidForBatsman.getRole()).price(maxPaidForBatsman.getPrice()).build());
		maxPaidPlayersOnEachRole.add(PlayerDTO.builder().name(maxPaidForWicketKeeper.getName())
				.role(maxPaidForWicketKeeper.getRole()).price(maxPaidForWicketKeeper.getPrice()).build());
		return maxPaidPlayersOnEachRole;
	}

	@Override
	public List<PlayerDTO> getAllPlayers() {
		List<List<Players>> listPlayers = teamList.stream().map(Team::getPlayers).collect(Collectors.toList());
		List<PlayerDTO> allPlayers = new ArrayList<PlayerDTO>();
		for (List<Players> players : listPlayers) {
			for (Players player : players) {
				allPlayers.add(PlayerDTO.builder().name(player.getName()).role(player.getRole())
						.price(player.getPrice()).build());
			}
		}
		return allPlayers;
	}
	
	
	

}
