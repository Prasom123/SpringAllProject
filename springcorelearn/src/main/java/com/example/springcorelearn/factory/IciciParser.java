package com.example.springcorelearn.factory;

public class IciciParser implements Parser {

	@Override
	public BankName getBankName(BankName bankName) {
		return bankName;
	}

	@Override
	public void Process(String fileName) {
	 System.out.println("ICICI Bank Processing"+fileName);
		
	}

}
