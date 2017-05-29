package com.app.model;

public class AdminShippingCount {
	
	private int total;
	private int shippingID;
	private String companyName;

	public int getShippingID() {
		return shippingID;
	}

	public void setShippingID(int shippingID) {
		this.shippingID = shippingID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
