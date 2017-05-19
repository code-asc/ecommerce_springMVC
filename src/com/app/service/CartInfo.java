package com.app.service;

import java.util.List;

import com.app.model.CartDisplayList;
import com.app.model.OrderDetailsInfo;

public interface CartInfo {

	public abstract void addToCart(String status, int userID, int productID);

	public abstract List<CartDisplayList> getCartList(String status, int userID);

	public abstract void incrementProduct(int userID, int detailID);

	public abstract void decrementProduct(int userID, int detailID);

	public abstract List<OrderDetailsInfo> getOrderDetailsInfo(int detailID, int userID);

}