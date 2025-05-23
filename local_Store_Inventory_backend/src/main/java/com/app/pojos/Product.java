package com.app.pojos;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString(exclude = "orderItems")
public class Product extends BaseEntity {
	
	@Column(length = 200, unique = true)
	private String name;
	
	@Column
	private double prize;
	
	@Column(length = 20)
	private double quantity;
	
	@Column(length = 300)
	private String imageName;
	
	@Column(length = 300)
	private String description;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
	List<OrderItem> orderItems;
	
	@ManyToOne
	@JoinColumn(name = "Category_id")
	private Category category;
	
	

}
