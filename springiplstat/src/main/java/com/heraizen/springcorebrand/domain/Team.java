package com.heraizen.springcorebrand.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
	
	@Id
	private String id;
	private String city;
	private String coach;
	private String home;
	private String name;
	private String label;
	private List<Players> players;
}
