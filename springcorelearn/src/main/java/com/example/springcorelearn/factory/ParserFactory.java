package com.example.springcorelearn.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ParserFactory {
	private Parser parser;
    
	@Autowired
	public ParserFactory(List<Parser> parser) {
	}
}
