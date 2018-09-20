package com.elcafetal.elcafetal.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_value")
	private Integer unitValue;
	
	@Column(name="is_perishable")
	private int isPerishable;
	
	@Column(name="date_purchase")
	private Date datePurchase;

	public Product() {		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Integer unitValue) {
		this.unitValue = unitValue;
	}
	
	public int getIsPerishable() {
		return isPerishable;
	}

	public void setIsPerishable(int isPerishable) {
		this.isPerishable = isPerishable;
	}
	
	public Date getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}	
}