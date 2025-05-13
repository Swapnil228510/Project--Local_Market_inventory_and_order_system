package com.app.imageConfig;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig { //configuring file storage location

	
    @Bean(name = "upload.dir")
    public String uploadDir() {
        String uploadPath = System.getProperty("user.dir") + "/uploads";
        new File(uploadPath).mkdirs(); // create if not exists
        return uploadPath;
    }
	

}
