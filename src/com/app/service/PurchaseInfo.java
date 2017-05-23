package com.app.service;

import java.util.List;

import com.app.model.PurchaseHistoryModel;

public interface PurchaseInfo {

	public abstract List<PurchaseHistoryModel> getPurchaseDetails(int userID, int start, int end);
	
	public abstract List<PurchaseHistoryModel> getTotalPurchaseList(int userID);
}