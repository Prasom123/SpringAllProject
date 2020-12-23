package com.heraizen.springftlexample.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
	private String pname;
	private String url;
	private double price;
	private double discount;
}
