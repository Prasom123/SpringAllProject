package com.heraizen.cj.service;

import java.util.HashMap;
import java.util.Map;


public class PhoneBookServiceImpl implements PhoneBookService {
	Map<String, String> map = new HashMap<>();
	private static PhoneBookServiceImpl phoneBoookServiceImpl = null;
	
	public static PhoneBookServiceImpl getInstance() {
		if(phoneBoookServiceImpl==null) {
			synchronized (PhoneBookServiceImpl.class) {
				if(phoneBoookServiceImpl==null) {
					phoneBoookServiceImpl=new PhoneBookServiceImpl();
				}
			}
		}
		return phoneBoookServiceImpl;
	} 
	
	@Override
	public void addDetails(String phno, String name) {
		this.map.put(phno, name);
	}

	@Override
	public String getName(String phno) {
		String name=map.get(phno);
		return name;
	}

}
