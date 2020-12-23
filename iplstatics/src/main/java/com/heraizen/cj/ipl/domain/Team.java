package com.heraizen.cj.ipl.domain;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
	private ObjectId id;
	private String city;
	private String coach;
	private String home;
	private String name;
	private String label;
	private List<Players> players;
}
