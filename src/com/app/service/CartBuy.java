package com.app.service;

public interface CartBuy {

	public abstract long cartBuyProduct(int userID, int addressID);

	public abstract void setOrderID(int userID, long orderID);

}