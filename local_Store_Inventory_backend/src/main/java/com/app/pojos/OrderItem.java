package com.app.pojos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orderItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString(exclude = "order") //  Important: avoid infinite recursion
public class OrderItem extends BaseEntity {

	@Column(length = 20)
	private double quantity;
	
	@Column(length = 20)
	private double unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
