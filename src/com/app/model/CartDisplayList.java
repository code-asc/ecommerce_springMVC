package com.app.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class CartDisplayList {

	private String brandName;
	private int detailProductID;
	private int quantity;
	private int supplierID;
	private int detailID;
	private String thumbNailPhoto;
	private BigDecimal afterDiscount;
	private String productName;
	private String supplierName;
	private String status;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getDetailProductID() {
		return detailProductID;
	}

	public void setDetailProductID(int detailProductID) {
		this.detailProductID = detailProductID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getDetailID() {
		return detailID;
	}

	public void setDetailID(int detailID) {
		this.detailID = detailID;
	}

	public String getThumbNailPhoto() {
		return thumbNailPhoto;
	}

	public void setThumbNailPhoto(String thumbNailPhoto) {
		this.thumbNailPhoto = thumbNailPhoto;
	}

	public BigDecimal getAfterDiscount() {
		return afterDiscount;
	}

	public void setAfterDiscount(BigDecimal afterDiscount) {
		this.afterDiscount = afterDiscount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
