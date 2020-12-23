package com.example.springcorelearn.factory;

public class SbiParser implements Parser {
	@Override
	public BankName getBankName(BankName bankName) {
		return bankName;
	}

	@Override
	public void Process(String fileName) {
	 System.out.println("SBI Bank Processing"+fileName);
		
	}
}
