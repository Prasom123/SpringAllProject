package com.example.springcorelearn.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class YamlReader {

	public  <T> List<T> loadYamlData(Class<?> t, String fileName){
		
		List<T> list=new ArrayList<>();
	  	Yaml yaml = new Yaml();
	    Iterable<Object> itr = yaml.loadAll(YamlReader.class.getResourceAsStream(fileName));
	    for(Object obj:itr) {
	    	@SuppressWarnings("unchecked")
			T ele=(T)obj;
	    	list.add(ele);
	    }
		
		return list;
	}
		
		
}
