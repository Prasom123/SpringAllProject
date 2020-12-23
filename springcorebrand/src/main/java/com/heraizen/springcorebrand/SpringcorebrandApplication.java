package com.heraizen.springcorebrand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.heraizen.springcorebrand.domain.Team;
import com.heraizen.springcorebrand.util.YamlReader;

@SpringBootApplication
public class SpringcorebrandApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringcorebrandApplication.class, args);
	}
	@Autowired
    private YamlReader yamlReader;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hi Badal");
//	  System.out.println(yamlReader.getYamlData());
	}
	
    
}
