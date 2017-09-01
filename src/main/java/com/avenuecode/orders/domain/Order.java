package com.avenuecode.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.math.BigDecimal.ROUND_FLOOR;
import static java.math.BigInteger.ZERO;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "orders")
@JsonInclude(NON_NULL)
public class Order implements Serializable {

	public static final int PRECISION = 2;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@JsonIgnore
	private String orderId;

	@Column(unique = true, nullable = false, length = 8)
	private String orderNumber;

	@Column
	private BigDecimal discount;

	@Column(nullable = false)
	private BigDecimal taxPercent;

	private BigDecimal total;

	private BigDecimal totalTax;

	private BigDecimal grandTotal;
	
	public Order() {
		
	}
	
	public Order(String orderId, String orderNumber, BigDecimal discount,
			BigDecimal taxPercent, BigDecimal total, BigDecimal totalTax,
			BigDecimal grandTotal, String status, List<Product> products) {
		super();
		this.orderId = orderId;
		this.orderNumber = orderNumber;
		this.discount = discount;
		this.taxPercent = taxPercent;
		this.total = total;
		this.totalTax = totalTax;
		this.grandTotal = grandTotal;
		this.status = status;
		this.products = products;
	}



	@Column(length = 10)
	private String status;

	@ManyToMany(fetch = EAGER, cascade = ALL)
	@JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id", updatable = false, nullable = false), inverseJoinColumns = @JoinColumn(name = "product_id", updatable = false, nullable = false))
	private List<Product> products = new ArrayList<>();

	public BigDecimal getTotal() {
		BigDecimal total = new BigDecimal(ZERO);
		for (Product product : products) {
			total = total.add(product.getPrice());
		}
		return scaled(total);
	}

	public BigDecimal getTotalTax() {
		return scaled(getTotal().multiply(
				taxPercent.divide(new BigDecimal("100"))));
	}

	public BigDecimal getGrandTotal() {
		BigDecimal total = this.getTotal().add(getTotalTax());
		if (discount != null) {
			return scaled(total.subtract(discount));
		}
		return scaled(total);
	}

	private BigDecimal scaled(BigDecimal value) {
		return value.setScale(PRECISION, ROUND_FLOOR);
	}

	public String getStatus() {
		return status;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	/**
	 * @param taxPercent the taxPercent to set
	 */
	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * @param totalTax the totalTax to set
	 */
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}

	/**
	 * @param grandTotal the grandTotal to set
	 */
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNumber=" + orderNumber
				+ ", discount=" + discount + ", taxPercent=" + taxPercent
				+ ", total=" + total + ", totalTax=" + totalTax
				+ ", grandTotal=" + grandTotal + ", status=" + status
				+ ", products=" + products + "]";
	}
	
	

}
