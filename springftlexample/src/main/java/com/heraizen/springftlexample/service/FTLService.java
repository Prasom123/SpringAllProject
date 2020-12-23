package com.heraizen.springftlexample.service;


import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.heraizen.springftlexample.domain.Product;
import com.heraizen.springftlexample.dto.ProdoctV1DTO;
import com.heraizen.springftlexample.dto.ProductV2DTO;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class FTLService {

	@Autowired
	private Configuration config;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JavaMailSender emailSender;

	public void sendOTP() throws Exception {
		Map<String, String> map = new HashMap<>();
        
		map.put("username", "Badal");
		map.put("application", "RRB7845877BH");
		map.put("otp", "894556");
		map.put("second", "30");
		Template temp = config.getTemplate("otp.ftl");
		StringWriter stringWriter = new StringWriter();
        temp.process(map, stringWriter);
        System.out.println(stringWriter);
	}
	
	public void sendNewProductToUser() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException     {
		
		Map<String, Object> map = new HashMap<>();
        
		List<String> userList=new ArrayList<String>();
		userList.add("Badal");
		userList.add("Prasom");
		userList.add("Pushkar");
		userList.add("Saurav");
		userList.add("Gaurav");

		Product p1=Product.builder().pname("Samsung A31").url("http://samsungA31").build();
		Product p2=Product.builder().pname("OPPO A31s").url("http://oppo").build();
		Product p3=Product.builder().pname("Vivo V20 pro").url("http://vivo").build();
		List<Product> productList=new ArrayList();
		
		productList.addAll(Arrays.asList(p1,p2,p3));
		
	       for(String user:userList)    {
			map.put("name", user);
			map.put("products", productList);
			Template template = config.getTemplate("newProduct.ftl");
			StringWriter stringWriter = new StringWriter();
	        template.process(map, stringWriter);
	        System.out.println(stringWriter);
	       }
	
		
	}
	
	public List<ProdoctV1DTO> getProductV1(){
		  Product p1 =Product.builder().pname("Lenovo Y21").url("http://lenovo.com").price(15000).discount(500).build();
		  Product p2 =Product.builder().pname("Asus A21").url("http://asus.com").price(12000).discount(600).build();
		  Product p3 =Product.builder().pname("Samsung M31s").url("http://samsung.com").price(20000).discount(1000).build();
		  Product p4 =Product.builder().pname("Vivo v21").url("http://vivo.com").price(25000).discount(800).build();
		  List<Product> listOfPrdouct=new ArrayList<Product>();
		  listOfPrdouct.addAll(Arrays.asList(p1,p2,p3,p4));
 		  List<ProdoctV1DTO> proudctV1List = Arrays.asList(modelMapper.map(listOfPrdouct, ProdoctV1DTO[].class));
 		  return proudctV1List;
		  
	}
	public List<ProductV2DTO> getProductV2(){
		  Product p1 =Product.builder().pname("Lenovo Y21").url("http://lenovo.com").price(15000).discount(500).build();
		  Product p2 =Product.builder().pname("Asus A21").url("http://asus.com").price(12000).discount(600).build();
		  Product p3 =Product.builder().pname("Samsung M31s").url("http://samsung.com").price(20000).discount(1000).build();
		  Product p4 =Product.builder().pname("Vivo v21").url("http://vivo.com").price(25000).discount(800).build();
		  List<Product> listOfPrdouct=new ArrayList<Product>();
		  listOfPrdouct.addAll(Arrays.asList(p1,p2,p3,p4));
		  List<ProductV2DTO> proudctV2List = Arrays.asList(modelMapper.map(listOfPrdouct, ProductV2DTO[].class));
		  return proudctV2List;
		  
	}
	public void sendMail(){
		  Map<String , Object> map=new HashMap();
		  Product p1 =Product.builder().pname("Lenovo Y21").url("http://lenovo.com").price(15000).discount(500).build();
		  Product p2 =Product.builder().pname("Asus A21").url("http://asus.com").price(12000).discount(600).build();
		  Product p3 =Product.builder().pname("Samsung M31s").url("http://samsung.com").price(20000).discount(1000).build();
		  Product p4 =Product.builder().pname("Vivo v21").url("http://vivo.com").price(25000).discount(800).build();
		  
		  Map<String, String> userList=new HashMap<String, String>();
		  userList.put("Badal", "badalraj2198@gmail.com");
		  userList.put("Prasom", "prasomkumarbadal@gmail.com");
		  
		  
		  
		  
	}
	
}
