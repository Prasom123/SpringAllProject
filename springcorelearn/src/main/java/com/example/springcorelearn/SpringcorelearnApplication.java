package com.example.springcorelearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springcorelearn.domain.User;
import com.example.springcorelearn.emicalcularor.AutoConfig;
import com.example.springcorelearn.emicalcularor.EmiCalculator;
import com.example.springcorelearn.greetingservice.GreetService;
import com.example.springcorelearn.util.UtilReader;
import com.example.springcorelearn.util.YamlReader;

@SpringBootApplication
public class SpringcorelearnApplication implements CommandLineRunner {
	@Autowired
	private UtilReader utilReader;


	@Qualifier("birthday")
	@Autowired
	private GreetService greetService1;
	@Qualifier("birthday")
	@Autowired
	private GreetService greetService2;

	@Qualifier("sessional")
	@Autowired
	private GreetService greetService3;
	
	@Autowired
	private YamlReader yamlReader;
	
//	@Qualifier("greetService1")
//	@Autowired
//	private GreetService greetService;

	public static void main(String[] args) {
		SpringApplication.run(SpringcorelearnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Hello Badal");
//		System.out.println("---------------------");
//		utilReader.getPlayers().stream().forEach(player -> {
//			System.out.println(player.getName());
//		});
//		System.out.println(emiCalculator.emiCalculate(200000, 20, 2));
		System.out.println(greetService1.greeting("Badal"));
		System.out.println(greetService2.greeting("Prasom"));
		System.out.println(greetService3.greeting("Prasom"));
//		System.out.println(greetService1);
//		System.out.println(greetService2);
		System.out.println(utilReader.getUser());
	    System.out.println(yamlReader.loadYamlData(User.class, "/init.yaml"));
	      
	}

}
