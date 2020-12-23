package com.heraizen.springcorebrand.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamDTO {
	private String name;
	private String coach;
	private String home;
	private String label;
	private String city;
}
