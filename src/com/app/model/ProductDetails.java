package com.app.model;

import java.math.BigDecimal;

public class ProductDetails {
	
	private int productID;
	private String productName;
	private String productDesc;
	private BigDecimal unitPrice;
	private String thumbNailPhoto;
	private String brandName;
	private  BigDecimal discount;
	
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getThumbNailPhoto() {
		return thumbNailPhoto;
	}
	public void setThumbNailPhoto(String thumbNailPhoto) {
		this.thumbNailPhoto = thumbNailPhoto;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
}
