package com.app.dto;

import lombok.Data;

@Data
public class PlacedOrderDetailsDto {

	private double quantity;
	
	private double unitPrice;
	
	//Product Name
	private String name;
}
