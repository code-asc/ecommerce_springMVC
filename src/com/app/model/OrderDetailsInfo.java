package com.app.model;

import java.math.BigDecimal;


public class OrderDetailsInfo {
	
	private BigDecimal detailPrice;
	private int quantity;
	private int totalCount;
	private int sum;
	private int totalPriceForEachProduct;
	public int getTotalPriceForEachProduct() {
		return totalPriceForEachProduct;
	}
	public void setTotalPriceForEachProduct(int totalPriceForEachProduct) {
		this.totalPriceForEachProduct = totalPriceForEachProduct;
	}
	public BigDecimal getDetailPrice() {
		return detailPrice;
	}
	public void setDetailPrice(BigDecimal detailPrice) {
		this.detailPrice = detailPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
}
