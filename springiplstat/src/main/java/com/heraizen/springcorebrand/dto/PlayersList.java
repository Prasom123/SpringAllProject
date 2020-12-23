package com.heraizen.springcorebrand.dto;

import java.util.List;

import com.heraizen.springcorebrand.domain.Players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PlayersList {
	private List<Players> players;
}
