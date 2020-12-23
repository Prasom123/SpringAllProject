package com.heraizen.springcorebrand.domain;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
	
	private String city;
	private String coach;
	private String home;
	private String name;
	private String label;
	private List<Players> players;
}
