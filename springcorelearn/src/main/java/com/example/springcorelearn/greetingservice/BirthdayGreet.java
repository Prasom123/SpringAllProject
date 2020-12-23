package com.example.springcorelearn.greetingservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Qualifier("birthday")
@Component
//@Scope(scopeName = "prototype")
public class BirthdayGreet implements GreetService{

	@Override
	public String greeting(String name) {
		
		return String.format("Hello %s",name+",\n\t wish you many many returns of day");
	}

}
