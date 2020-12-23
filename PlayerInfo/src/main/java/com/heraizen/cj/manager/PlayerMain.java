package com.heraizen.cj.manager;

import java.util.Scanner;

import com.heraizen.cj.service.PlayerService;
import com.heraizen.cj.service.PlayerServiceImpl;

public class PlayerMain {
public static void main(String[] args) {
//	 PlayerService playerService=PlayerServiceImpl.getInstance();
//	 playerService.viewPlayers();
	 Scanner X = new Scanner(System.in);
     
     double principal, rate, time, emi;

     System.out.print("Enter principal: ");
     principal = X.nextDouble();
   
     System.out.print("Enter rate: ");
     rate = X.nextDouble();
   
     System.out.print("Enter time in year: ");
     time = X.nextDouble();
   
     rate=rate/(12*100); /*one month interest*/
     time=time*12; /*one month period*/
   
   
     emi= (principal*rate*Math.pow(1+rate,time))/(Math.pow(1+rate,time)-1);
   
     System.out.print("Monthly EMI is= "+emi+"\n");
}
}
