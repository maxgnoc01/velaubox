package com.shoppingCart.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "OrderDetail")
@Table(name = "order_detail", catalog = "velaubox2")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderDetailId;
	@Column
	private Long productId;
//	@Column
//	private Long orderId;
	@Column 
	private Long quantity;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	private Order order;

}
