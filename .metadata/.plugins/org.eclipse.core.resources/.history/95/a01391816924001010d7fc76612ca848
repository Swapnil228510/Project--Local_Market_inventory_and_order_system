package com.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.FoundByIdDto;
import com.app.dto.RegisterUserDto;
import com.app.dto.SignInDto;
import com.app.exception.ResourceNotFoundException;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private ModelMapper mapper;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	@Override
	public RegisterUserDto registerUser(RegisterUserDto registerRequest) {
		User user = mapper.map(registerRequest, User.class);
		
//		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRole(Role.CUSTOMER);
		
		User savedUser = userdao.save(user);

		return mapper.map(savedUser,RegisterUserDto.class);
	}


	@Override
	public User signInUser(String email, String password) {
		User user = userdao.findByEmailAndPassword(email, password).orElseThrow(()-> new RuntimeException("User needs to register"));
		return user;
	}


	@Override
	public User getById(Long id) {
		User user = userdao.findById(id).orElseThrow(()->new ResourceNotFoundException("Given Id is not present"));
//		System.out.println("User is found "+user);
		return user;
	}


	@Override
	public List<User> getAllUser() {
		List<User> list = userdao.findAll();
//		list.forEach(System.out::println);
		return list;
	}


//	@Override
//	public FoundByIdDto updateUser(FoundByIdDto updateRequestData, Long id) {
//		User user = mapper.map(updateRequestData, User.class);
//		user.setPassword(userdao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id is not present")).getPassword());
//		
//		System.out.println("lsknfkjadnjkfndskf "+ user);
//		User updatedUser = userdao.save(user);
//		return mapper.map(updatedUser, FoundByIdDto.class);
//	}
	
	//we can use above or below method for update as per requirement
	@Override
	public FoundByIdDto updateUser(FoundByIdDto updateRequestData, Long id) {
//		User user = mapper.map(updateRequestData, User.class);
		
		User u = userdao.findById(id).orElse(null);
		
		u.setFirstName(updateRequestData.getFirstName());
		u.setLastName(updateRequestData.getLastName());
		u.setGender(updateRequestData.getGender());
		u.setDob(updateRequestData.getDob());
		u.setMobNo(updateRequestData.getMobNo());
		u.setEmail(updateRequestData.getEmail());
		u.setCity(updateRequestData.getCity());
		
		return mapper.map(u, FoundByIdDto.class);
	}


	@Override
	public ApiResponse deleteUser(Long id) {
		User user = userdao.findById(id).orElseThrow(()->new ResourceNotFoundException("Given Id is not present"));
		if(user != null) {
			userdao.deleteById(id);
			return new ApiResponse("User is deleted", true);
		}
		return new ApiResponse("No such user present with such ID", false);
	}


}



















