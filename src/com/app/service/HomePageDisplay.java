package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;
import com.app.repository.HomePageData;


@Service
public class HomePageDisplay {
	
	@Autowired
	private HomePageData homePageData;
	
	
	public List<HomePageLargeImage> homePageDisplay()
	{
		return homePageData.homePageContent();
		
	}
	
	public List<HomePageThumbNail> homePageDisplayThumbNail()
	{
		return homePageData.homePageContentThumbNail();
	}

}
