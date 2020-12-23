package com.heraizen.springiplstat.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heraizen.springcorebrand.domain.Team;


public final class utilJsonReader {
	private  utilJsonReader(){
		
	}
	public  static List<Team> getJsonData() {
		List<Team> teamList = new ArrayList<Team>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			teamList = mapper.readValue(utilJsonReader.class.getResourceAsStream("/iplinfo.json"),
					new TypeReference<List<Team>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teamList;
	}
}
