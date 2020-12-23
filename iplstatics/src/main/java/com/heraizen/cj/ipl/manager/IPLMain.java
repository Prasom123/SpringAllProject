package com.heraizen.cj.ipl.manager;

import com.heraizen.cj.ipl.service.IplNewService;
import com.heraizen.cj.ipl.service.IplNewServiceImpl;

public class IPLMain {
	public static void main(String[] args)  {
//    IplStatOperation iplStatOperationObj=new IplStatOperation();
//	  iplStatOperationObj.start();
	   
	 IplNewService iplService=IplNewServiceImpl.getInstance();
	  IplStatNewOperation iplStatOperation = new IplStatNewOperation();
	  iplStatOperation.start();
	  
	}
}
