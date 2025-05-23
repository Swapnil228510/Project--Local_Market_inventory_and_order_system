package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDto;
import com.app.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// STAFF API
	@PreAuthorize("hasRole('STAFF')")
	@PostMapping("/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategories(categoryDto));
		
	}
	
	
	@GetMapping("/allCategories")
	public ResponseEntity<?> getAllCategories(){
		
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.ListOfCategory());
		
	}
	
	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/updateCategory/{id}")
	public ResponseEntity<?> updateCategoryName(@RequestBody CategoryDto updatedNameDto, @PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(updatedNameDto, id));
		
	}
	
	@PreAuthorize("hasAnyRole('STAFF','ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(id));
	}
	
	
	

}
