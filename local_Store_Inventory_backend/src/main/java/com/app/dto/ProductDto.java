package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Long id;

	private String name;
	
	private double prize;
	
	private double quantity;
	
	private Long categoryId;
	
}
