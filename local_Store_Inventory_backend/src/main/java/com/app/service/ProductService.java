package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.pojos.Product;

public interface ProductService {

	ApiResponse addProductDetails(ProductDto productDetailsdto);
	
	List<ProductDto>  getAllProduct();
	
	ProductDto getProducBytId(Long id);
	
	ApiResponse updateProduct(ProductDto updatedPrductinfo, Long id);
	
	ApiResponse deleteProduct(Long id);
	
	List<ProductDto> getProductsByCategoryId(Long categoryId);
	
//	==================================
	
	Product save(Product product);
	
	
}
