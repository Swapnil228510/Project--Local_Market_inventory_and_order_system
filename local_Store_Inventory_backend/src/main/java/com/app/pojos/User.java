package com.app.pojos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "password")
public class User extends BaseEntity{

	@NotBlank(message = "First Name should not be blank")
	@Column(length = 20)
	private String firstName;
	
	@NotBlank(message = "Last Name should not be blank")
	@Column(length = 20)
	private String lastName;
	
	@Column(length = 10)
	private String gender;
	
	@NotNull(message = "Date of Birth should not be blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	@NotBlank(message = "Mobile number should not be blank")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
	@Column(length = 20,unique = true)
	private String mobNo;
	
	@Email @NotBlank(message = "Email should not be blank")
	@Column(length = 80,unique = true)
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String password;
	
	@Column(length = 20)
	private String city;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderEntity> orders;
	
}
