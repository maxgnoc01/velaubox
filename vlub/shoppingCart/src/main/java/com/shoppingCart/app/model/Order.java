package com.shoppingCart.app.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.shoppingCart.app.util.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Order")
@Table(name = "orders", catalog = "velaubox2")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order implements java.io.Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@Column
	private String customerName;
	@Column
	private String address;
	@Column
	private Date orderedDate;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private OrderStatus status;
	@Column
	private BigDecimal total;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "order", orphanRemoval = true)
	private List<OrderDetail> listItem;
	
}
