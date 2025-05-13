package com.app.imageConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { //serving image
	
	@Value("${upload.dir}")
	private String uploadDir;
	
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	            .addResourceHandler("/uploads/**")
	            .addResourceLocations("file:" + uploadDir + "/");
	    }
	

}
