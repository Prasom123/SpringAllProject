package com.example.springcorelearn.greetingservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("sessional")
@Component
public class SessionalGreet implements GreetService{
	@Override
	public String greeting(String name) {
		
		return String.format("Hello %s",name+",\n\t Wishing you a season of peace filled with love and happiness.\r\n" + 
				"");
	}
}
