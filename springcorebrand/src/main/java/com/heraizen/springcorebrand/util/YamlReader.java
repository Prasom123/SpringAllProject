package com.heraizen.springcorebrand.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import com.heraizen.springcorebrand.domain.Team;



@Component

public class YamlReader {
	public  List<Team> getYamlData() {
		List<Team> teamList = new ArrayList<Team>();
		Yaml yaml = new Yaml();
		   teamList= Arrays.asList(yaml.loadAs(YamlReader.class.getResourceAsStream("/ipl2020.yaml") ,Team[].class ));
	  
		return teamList;
	}
}
