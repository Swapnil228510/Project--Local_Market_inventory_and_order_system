package com.app.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ComboPlacedOrderDto {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	private double totalAmount;
	
	private List<PlacedOrderDetailsDto> placedOrderDetailsDto;
}
