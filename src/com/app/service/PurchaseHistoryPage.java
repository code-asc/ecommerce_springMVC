package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.model.PurchaseHistoryModel;
import com.app.repository.PurchaseHistory;

@Service
public class PurchaseHistoryPage implements PurchaseInfo {

	@Autowired
	PurchaseHistory purchaseInfo;
	
	/* (non-Javadoc)
	 * @see com.app.service.PurchaseInfo#getPurchaseDetails(int, int, int)
	 */
	@Override
	public List<PurchaseHistoryModel> getPurchaseDetails(int userID , int start , int end)
	{
		List<Integer> list = purchaseInfo.getPurchaseHistoryID(userID, start, end);
		StringBuilder detailID = new StringBuilder();
		for(int index = 0 ; index < list.size() ; index++)
		{
			detailID.append(list.get(index));
			if(index != list.size()-1)
			{
				detailID.append(" , ");
			}
		}
		
		
		return purchaseInfo.getPurchaseHistory(userID, detailID.toString());
	}
	
	
	/* (non-Javadoc)
	 * @see com.app.service.PurchaseInfo#getTotalPurchaseList(int)
	 */
	@Override
	public List<PurchaseHistoryModel> getTotalPurchaseList(int userID)
	{
		return purchaseInfo.getCustomerPurchaseHistory(userID);
	}
}
