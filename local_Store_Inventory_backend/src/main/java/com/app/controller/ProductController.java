package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.pojos.Product;
import com.app.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private  ProductService productService;
	
    @Value("${upload.dir}")
    private String uploadDir;
	
	//Staff API
	@PreAuthorize("hasRole('STAFF')")
	@PostMapping("/addProducts")
	public ResponseEntity<?> AddProductDetails(@RequestBody ProductDto productDetailsdto){
		
		System.out.println(" in addproduct cont "+productDetailsdto.getCategoryId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProductDetails(productDetailsdto));
		
	}
	
//	@GetMapping
//	public ResponseEntity<?> showAllProducts(){
//		
//		return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProducBytId(id));
		
	}
	
	//STAFF API
	@PreAuthorize("hasRole('STAFF')")
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<?> updatedPrduct(@RequestBody ProductDto updatedProdDto, @PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(updatedProdDto, id));
	}
	
	//ADMIN API
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
	}
	
	
	@GetMapping("/byCategoryId/{categoryId}")
	public ResponseEntity<?> findProductByCategoryId(@PathVariable Long categoryId){
		
		List<ProductDto> prodDtoList = productService.getProductsByCategoryId(categoryId);
		if(prodDtoList.size() !=0) {
			return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategoryId(categoryId));
		} else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Invalid category Id", false));
	}
	
//	============================================================ below is image handling API
	
	@PostMapping("/post")
	public ResponseEntity<?> addProduct(
			@RequestParam("name") String name,           
			@RequestParam("prize") double prize,
            @RequestParam("quantity") int quantity,
            @RequestParam("categoryName") String categoryName,
            @RequestParam("image") MultipartFile imageFile
			
			)throws IOException{
		
        String imageName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path imagePath = Paths.get(uploadDir, imageName);
        Files.copy(imageFile.getInputStream(), imagePath);
        
        Product product = new Product();
        product.setName(name);
        product.setPrize(prize);
        product.setQuantity(quantity);
        product.getCategory().setName(categoryName);
        product.setImageName(imageName);
        
        Product savedProduct = productService.save(product);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        
        
	}

	@GetMapping
    public ResponseEntity <List<ProductDto>> getAllProducts() {
         return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProduct());
        }
		
}
