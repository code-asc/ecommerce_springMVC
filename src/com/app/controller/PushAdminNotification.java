package com.app.controller;

import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.app.model.NotificationModel;
import com.app.service.NotificationService;

@Controller
/**
 * The PushAdminNotification class is used to push notifications to the customer pages using server sent events
 */
public class PushAdminNotification {
	
	@Autowired
	NotificationService services;
	
	
	List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	
	
	/**
	 *  adminPostHandler method will return an SseEmitter object.
	 */
	@RequestMapping(value = "/emitterMapping" )
	public SseEmitter adminPostHandler()
	{
		
		SseEmitter emitter = new SseEmitter(6000000L);
		emitters.add(emitter);
		emitter.onCompletion(() -> emitters.remove(emitter));
		return emitter;
	}
	
	/**
	 *  adminPost method provides admin to post the notifications.
	 * @param notification of type String
	 */
	
	@RequestMapping(value = "/adminPost" , method = RequestMethod.GET)
	public @ResponseBody int adminPost(@RequestParam("notification") String notification)
	{
		int check = 1;
		services.insertNotidicationIntoDB(notification);
		System.out.println("the size : "+emitters.size());
		for(SseEmitter emitter : emitters)
		{
			try{
				emitter.send(SseEmitter.event().name("spring").data(notification) );
				emitter.complete();
				
			}
			catch(SocketException e)
			{
				e.printStackTrace();
			}catch(IOException e)
			{
				e.printStackTrace();
			}catch(Exception e)
			{
				e.printStackTrace();
				check = 0;
			}
		}
		return check;
	}
	
	
	/**
	 *  getNotificationInHeader method returns all the notifications.
	 */
	@RequestMapping(value = "/getNotification" , method = RequestMethod.GET)
	public @ResponseBody List<NotificationModel> getNotificationInHeader()
	{
		return services.getNotificationService();
	}
	
	
	/**
	 *  getMarkAsRead method changes the notification status as read.
	 */
	@RequestMapping(value = "/markAsRead" , method = RequestMethod.GET)
	public @ResponseBody void getMarkAsRead()
	{
		services.markNotificationAsRead();
	}
	
}
