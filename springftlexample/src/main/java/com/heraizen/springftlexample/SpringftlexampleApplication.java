package com.heraizen.springftlexample;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.heraizen.springftlexample.service.FTLService;


@SpringBootApplication
public class SpringftlexampleApplication implements CommandLineRunner {

	@Autowired
	private FTLService otpService;

	public static void main(String[] args) {
		SpringApplication.run(SpringftlexampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
   //  otpService.sendOTP();
     otpService.sendNewProductToUser();
	}

}
