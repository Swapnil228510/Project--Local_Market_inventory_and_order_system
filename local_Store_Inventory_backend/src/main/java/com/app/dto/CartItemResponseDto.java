package com.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemResponseDto {
	
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subtotal;

}
