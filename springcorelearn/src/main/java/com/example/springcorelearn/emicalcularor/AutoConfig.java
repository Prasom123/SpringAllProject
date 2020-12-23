package com.example.springcorelearn.emicalcularor;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.example.springcorelearn.domain.User;
import com.example.springcorelearn.greetingservice.BirthdayGreet;
import com.example.springcorelearn.greetingservice.SessionalGreet;

@Component
public class AutoConfig {
	@Bean
	public EmiCalculator emiCalculator() {
		return new EmiCalculator();
	}

//	List<User> user;
//	@Qualifier("birthday")
//	@Bean
//	public BirthdayGreet greetService1() {
//	 return new BirthdayGreet();
//	}
//	@Bean
//	public SessionalGreet greetService2() {
//	 return new SessionalGreet();
//	}

//	public List<User> getUser() {
//		return user;
//	}
//
//	public void setUser(List<User> user) {
//		this.user = user;
//	}
}
