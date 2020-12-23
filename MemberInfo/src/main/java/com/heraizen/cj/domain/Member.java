package com.heraizen.cj.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private String mid, name, city, country;
	private Member(String mid,String name , String city, String country) {
		 this.mid=IDGenerator.getNewId();
		 this.name=name;
		 this.city=city;
		 this.country=country;
	}
}
