package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ProductDao;
import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Category;
import com.app.pojos.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ModelMapper mapper;
	
    @Value("${server.base-url}")
    private String baseUrl; // e.g., http://localhost:6162  for image 

	@Override
	public ApiResponse addProductDetails(ProductDto productDetailsdto) {
		Product productdetails =  mapper.map(productDetailsdto, Product.class);
		
		Category category = new Category(); // in Product.pojo ,category takes object so we create its object and reassign the same id 
		category.setId(productDetailsdto.getCategoryId());
		productdetails.setCategory(category);
		
		productDao.save(productdetails);
		return new ApiResponse(" Product details has been added", true);
	}

//	@Override
//	public List<ProductDto>  getAllProduct() {
//		List<Product> productList =  productDao.findAll();
//		
//		List<ProductDto> allProductList = new ArrayList<ProductDto>();
//		  for(Product product : productList) {
//			  
//			  ProductDto prod = (mapper.map(product, ProductDto.class));
//			  prod.setCategoryId(product.getCategory().getId());
//			  prod.setCategoryName(product.getCategory().getName());
//			  allProductList.add(prod);			  		  
//		  }		
//
//		return allProductList;
//	}
//	========================================================
	@Override
	public Product save(Product product) {
		return productDao.save(product);
	}
	
	@Override
	public List<ProductDto>  getAllProduct() {

		return productDao.findAll().stream().map(product ->{
			ProductDto dto = new ProductDto();
			dto.setId(product.getId());
			dto.setName(product.getName());
			dto.setPrize(product.getPrize());
			dto.setQuantity(product.getQuantity());
			dto.setCategoryId(product.getCategory().getId());
			dto.setCategoryName(product.getCategory().getName());
			dto.setImageUrl(baseUrl+"/uploads/"+product.getImageName());
			return dto;
		}).collect(Collectors.toList());
	}

//	=================================================
	@Override
	public ProductDto getProducBytId(Long id) {
		
		Product product =  productDao.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Invalid Product Id "));
//		System.out.println(" product "+ product);
		
		ProductDto prodDto =  mapper.map(product, ProductDto.class);
		prodDto.setCategoryId(product.getCategory().getId());
		
		return prodDto;
	}

	@Override
	public ApiResponse updateProduct(ProductDto updatedPrductinfo, Long id) {
		Product product = productDao.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Invalid Id entered "));
		
		product.setName(updatedPrductinfo.getName());
		product.setPrize(updatedPrductinfo.getPrize());
		product.setQuantity(updatedPrductinfo.getQuantity());
		
		Category category = new Category();
		category.setId(updatedPrductinfo.getCategoryId());
		product.setCategory(category);
		
		return new ApiResponse(" Product is Updated ",true);
	}

	@Override
	public ApiResponse deleteProduct(Long id) {
		productDao.deleteById(id);
		return new ApiResponse(" Product is removed ",true);
	}

//	@Override
//	public ProductDto getProductsByCategoryId(Long categoryId) {
//		List<Product> productList =  productDao.findByCategoryId(categoryId);
//		
//		return null;
//	}
	
	@Override
	public List<ProductDto> getProductsByCategoryId(Long categoryId) {
		List<Product> productList =  productDao.findByCategoryId(categoryId);
		
		List<ProductDto> productDtoListByCategoryId = new ArrayList<ProductDto>();
		
		for(Product prod : productList) {
			
			ProductDto prodDto = mapper.map(prod, ProductDto.class);
			prodDto.setCategoryId(prod.getCategory().getId());
			productDtoListByCategoryId.add(prodDto);
		}
		
		return productDtoListByCategoryId;
	}

}
