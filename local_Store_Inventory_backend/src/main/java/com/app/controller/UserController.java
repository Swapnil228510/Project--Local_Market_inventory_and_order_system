package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.FoundByIdDto;
import com.app.dto.RegisterUserDto;
import com.app.dto.SignInDto;
import com.app.pojos.User;
import com.app.service.UserService;
import com.app.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper mapper;
	
	
	
	@PostMapping("/signUp")
	public ResponseEntity<?> userSignUp(@RequestBody @Valid RegisterUserDto registerRequest) {
		
		RegisterUserDto savedUser = null;
		
		savedUser = userService.registerUser(registerRequest);
//		System.out.println("sadfsfsf register user"+ savedUser);
		
		if(savedUser != null) {
			return  ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User "+savedUser.getFirstName()+ " "+savedUser.getLastName() +" successfully registered",true));
		}
		return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User not register",false));
	}
		
	
	// CREATED SIGNIN WITH SPRING SECURITY
//	@PostMapping("/signIn")
//	public ResponseEntity<?> SignInUserCont (@RequestBody SignInDto signInDto) {
//		User user = userService.signInUser(signInDto.getEmail(), signInDto.getPassword());
//		
//		if(user != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("User is present", true));
//		}else 
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User needs to register", false)); 
//	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  getProfileById(@PathVariable Long id) {
		User user = userService.getById(id);
		FoundByIdDto getUserDto = mapper.map(user, FoundByIdDto.class);
		
		
		if(user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(getUserDto);
		}else 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Invalid Id", false)); 
	}
	
	//ADMIN API
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getUsers")
	public ResponseEntity<?> showAllUser() {
		List<User> userList = userService.getAllUser();
		System.out.println("@@@@@ Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

		List<FoundByIdDto> userDtoList = new ArrayList<FoundByIdDto>();
		
		if(userList.size() != 0) {
		for(User u : userList) {
			userDtoList.add(mapper.map(u, FoundByIdDto.class));
		}
//			List<FoundByIdDto> userDtoList = userList.stream() //with streams()
//					.map(user -> mapper.map(user, FoundByIdDto.class))
//					.collect(Collectors.toList());
		
//			//FoundByIdDto : this used here bcoz there is no need to create another DTO for it.
		
			return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No User has registered yet", false)); 	
		
	}
	
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUserWithId(@RequestBody FoundByIdDto userDto, @PathVariable Long id) {
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userDto, id));
		
	}
	
	//ADMIN API
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/removeUser/{id}")
	public ResponseEntity<?> deleteUserById( @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
	}

}
