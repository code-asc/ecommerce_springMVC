package com.app.service;

public interface CartBuy {

	public abstract long cartBuyProduct(int userID, long addressID);

	public abstract void setOrderID(int userID, long orderID);

}