package com.heraizen.cj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import com.heraizen.cj.player.Player;


public class PlayerServiceImpl implements PlayerService {
	private static PlayerServiceImpl playerServiceImpl=null;
	List<Player> football = new ArrayList<>();
	List<Player> cricket =new ArrayList<>();

	private PlayerServiceImpl() {
		this.football.addAll(this.getFootBallPlayerData());
		this.cricket.addAll(getCricketPlayerData());

	}
	public static PlayerServiceImpl getInstance() {
		if (playerServiceImpl == null) {
			synchronized (PlayerServiceImpl.class) {
				if (playerServiceImpl == null) {
					playerServiceImpl = new PlayerServiceImpl();
				}
			}
		}
		return playerServiceImpl;
	}

	public void viewPlayers() {
		List<String> commonPlayerNames=this.getPlayers(this.football, this.cricket);
		ListIterator<String> iterator=commonPlayerNames.listIterator();
		System.out.println("Player Name");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	public List<String> getPlayers(List<Player> football, List<Player> cricket) {
		List<Player> commonPlayers = football.stream().filter(cricket::contains).collect(Collectors.toList());
		List<String> playerNames=commonPlayers.stream().map(p -> p.getName()).collect(Collectors.toList());
		Collections.sort(playerNames);
		return playerNames;
	}

	private Collection<? extends Player> getFootBallPlayerData() {
		Player player1 = Player.builder().name("Badal").email("badal@gmail.com").city("Begusarai").state("Bihar")
				.age(22).build();
		Player player2 = Player.builder().name("Prasom").email("prasom@gmail.com").city("Begusarai").state("Bihar")
				.age(24).build();
		Player player3 = Player.builder().name("Nanhe").email("nanhe@gmail.com").city("Begusarai").state("Bihar")
				.age(28).build();
		Player player4 = Player.builder().name("Rohit").email("rohit@gmail.com").city("Begusarai").state("Bihar")
				.age(21).build();
		return Arrays.asList(new Player[] { player1, player2, player3, player4 });
	}

	private Collection<? extends Player> getCricketPlayerData() {
		Player player1 = Player.builder().name("Badal").email("badal@gmail.com").city("Begusarai").state("Bihar")
				.age(22).build();
		Player player2 = Player.builder().name("Pushkar").email("pushkar@gmail.com").city("Begusarai").state("Bihar")
				.age(25).build();
		Player player3 = Player.builder().name("Nanhe").email("nanhe@gmail.com").city("Begusarai").state("Bihar")
				.age(28).build();
		Player player4 = Player.builder().name("Mohit").email("mohit@gmail.com").city("Begusarai").state("Bihar")
				.age(24).build();
		return Arrays.asList(new Player[] { player1, player2, player3, player4 });
	}
}
