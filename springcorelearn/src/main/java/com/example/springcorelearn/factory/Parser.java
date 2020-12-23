package com.example.springcorelearn.factory;

public interface Parser {
  public BankName getBankName(BankName bankName) ;
  public void Process(String fileName) ;
}
