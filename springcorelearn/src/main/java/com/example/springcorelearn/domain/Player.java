package com.example.springcorelearn.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	private String name;
	private double price;
	private String role;
}
