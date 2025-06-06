package com.app.service;


import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.FoundByIdDto;
import com.app.dto.RegisterUserDto;
import com.app.pojos.User;

public interface UserService {

	RegisterUserDto registerUser(RegisterUserDto registerRequest);
	
	User signInUser(String email, String password);
	
	User getById(Long id);
	
	List<User> getAllUser();
	
	FoundByIdDto updateUser(FoundByIdDto updateRequestData, Long id);
	
	ApiResponse deleteUser(Long id);
}
