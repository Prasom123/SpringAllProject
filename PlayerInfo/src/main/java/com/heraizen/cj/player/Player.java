package com.heraizen.cj.player;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	String name,email,city,state;
	int age;
}
