package com.shoppingCart.app.model;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products", catalog = "velaubox2")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements java.io.Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	@Column
	private String productName;
	@Column
	private String description;
	@Column
	private BigDecimal price;
	@Column
	private Long iventoryNumber;

}
