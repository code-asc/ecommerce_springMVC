package com.app.model;

import java.math.BigDecimal;
import java.sql.Date;

public class PurchaseHistoryModel {
	
	private String customerAddress1;
	private String customerAddress2;
	private String customerCity;
	private int detailID;
	private String customerState;
	private String customerCountry;
	private int orderID;
	private BigDecimal orderAmount;
	private String orderDate;
	private BigDecimal detailPrice;
	private String brandName;
	private String productName;
	private BigDecimal afterDiscount;
	private String thumbNailPhoto;
	private String status;
	private int quantity;
	
	public String getCustomerAddress1() {
		return customerAddress1;
	}
	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}
	public String getCustomerAddress2() {
		return customerAddress2;
	}
	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}
	public String getCustomerCity() {
		return customerCity;
	}
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}
	public int getDetailID() {
		return detailID;
	}
	public void setDetailID(int detailID) {
		this.detailID = detailID;
	}
	public String getCustomerState() {
		return customerState;
	}
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}
	public String getCustomerCountry() {
		return customerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getDetailPrice() {
		return detailPrice;
	}
	public void setDetailPrice(BigDecimal detailPrice) {
		this.detailPrice = detailPrice;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getAfterDiscount() {
		return afterDiscount;
	}
	public void setAfterDiscount(BigDecimal afterDiscount) {
		this.afterDiscount = afterDiscount;
	}
	public String getThumbNailPhoto() {
		return thumbNailPhoto;
	}
	public void setThumbNailPhoto(String thumbNailPhoto) {
		this.thumbNailPhoto = thumbNailPhoto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	
	
	
	
	
}
