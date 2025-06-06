package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.CategoryDao;
import com.app.dto.ApiResponse;
import com.app.dto.CategoryDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addCategories(CategoryDto categoryDto) {

		Category category = mapper.map(categoryDto, Category.class);

		categoryDao.save(category);
		return new ApiResponse(" Category has been added", true);
	}

	@Override
	public List<CategoryDto> ListOfCategory() {
	 
		List<Category> categoryList = categoryDao.findAll();
		List<CategoryDto> totalCategoryList = new ArrayList<>();
		
		for(Category cat : categoryList) {
			totalCategoryList.add(mapper.map(cat, CategoryDto.class));
		}
		
		return totalCategoryList;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto updatedName, Long id) {
		Category categoyDetails = categoryDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID is not present"));
		
		categoyDetails.setName(updatedName.getName());
		
		return mapper.map(categoyDetails, CategoryDto.class);
	}

	@Override
	public ApiResponse deleteCategory(Long id) {

		categoryDao.deleteById(id);
		return new ApiResponse("Category is deleted ", true);
	}

}
