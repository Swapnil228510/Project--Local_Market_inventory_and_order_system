package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;

public interface CategoryService {
	
	ApiResponse addCategories(CategoryDto categoryDto);
	
	List<CategoryDto> ListOfCategory();
	
	CategoryDto updateCategory(CategoryDto updatedName, Long id );
	
	ApiResponse deleteCategory(Long id);

}
