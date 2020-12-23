package com.heraizen.cj.ipl.util;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heraizen.cj.ipl.domain.Team;


public final class ReaderUtil {
	private  ReaderUtil() {
	
		
	}
	public static List<Team> getTypeData(){
		   return getJsonData();
	}
	public static List<Team> getTypeData(FileType fileType){
		   if(fileType.equals(FileType.Json)) {
			   return getJsonData();
		   } else if(fileType.equals(FileType.Yaml)){
			   
			   return getYamlData();
		   } else {
			   return null;
		   }
		
	}
	private  static List<Team> getJsonData() {
		List<Team> teamList = new ArrayList<Team>();

		ObjectMapper mapper = new ObjectMapper();

		try {
			teamList = mapper.readValue(ReaderUtil.class.getResourceAsStream("/iplinfo.json"),
					new TypeReference<List<Team>>() {
					});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teamList;
	}
	private static List<Team> getYamlData() {
		List<Team> teamList = new ArrayList<Team>();
		Yaml yaml = new Yaml();
		   teamList= Arrays.asList(yaml.loadAs(ReaderUtil.class.getResourceAsStream("/ipl2020.yaml") ,Team[].class ));
	  
		return teamList;
	}
}
