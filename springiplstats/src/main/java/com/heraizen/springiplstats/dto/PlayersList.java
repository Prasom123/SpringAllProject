package com.heraizen.springiplstats.dto;

import java.util.List;

import com.heraizen.springiplstats.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlayersList {
	private List<Player> players;
}
