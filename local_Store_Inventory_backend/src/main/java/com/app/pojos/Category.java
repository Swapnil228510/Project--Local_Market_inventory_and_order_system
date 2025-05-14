package com.app.pojos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category extends BaseEntity {

	@Column(length = 20, unique = true)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "category", fetch = FetchType.EAGER)
	private List<Product> productList;
}
