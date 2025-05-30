package com.app.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterUserDto {

	@NotBlank(message = "first name can't be blank")
	private String firstName;

	@NotBlank(message = "last name can't be blank")
	private String lastName;

	@Email
	private String email;
	
	//@NotNull
	//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "invalid password format!!!!")
	private String password;

	@Pattern(regexp = "^[0-9]{10}$" ,message = "invalid mobile format!!!!")
	private String mobNo;
	
	private String city;
	
	private LocalDate dob;
	
	private String gender;
}
