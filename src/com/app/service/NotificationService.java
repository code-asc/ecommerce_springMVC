package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.NotificationModel;
import com.app.repository.AdminEdit;

@Service
public class NotificationService {
	
	@Autowired
	AdminEdit info;
	
	public List<NotificationModel> getNotificationService()
	{
		return info.notificationQuery();
	}
	
	public void insertNotidicationIntoDB(String content)
	{
		info.insertNotificationDataQuery(content);
	}
	
	public void markNotificationAsRead()
	{
		info.markAsReadNotificationQuery();;
	}
}
