package com.example.springcorelearn.factory;

public class HdfcParser implements Parser {
	@Override
	public BankName getBankName(BankName bankName) {
		return bankName;
	}

	@Override
	public void Process(String fileName) {
	 System.out.println("HDFC Bank Processing"+fileName);
		
	}
}
