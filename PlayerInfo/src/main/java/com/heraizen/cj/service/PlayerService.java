package com.heraizen.cj.service;


import java.util.List;

import com.heraizen.cj.player.Player;

public interface PlayerService {
	public List<String> getPlayers(List<Player> football, List<Player> cricket);
	public void viewPlayers();
	
}
