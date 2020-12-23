package com.heraizen.springcorebrand.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Players {
	private String name;
	private double price;
	private String role;
}
