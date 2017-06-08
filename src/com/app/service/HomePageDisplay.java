package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;
import com.app.repository.HomePageData;


@Service
public class HomePageDisplay implements HomePageInfo {
	
	@Autowired
	private HomePageData homePageData;
	
	
	/* (non-Javadoc)
	 * @see com.app.service.HomePageInfo#homePageDisplay()
	 */
	@Override
	@Cacheable(value="homePageImageLarge")
	public List<HomePageLargeImage> homePageDisplay()
	{
		return homePageData.homePageContent();
		
	}
	
	/* (non-Javadoc)
	 * @see com.app.service.HomePageInfo#homePageDisplayThumbNail()
	 */
	@Override
	@Cacheable(value="homePageImageThumbNail")
	public List<HomePageThumbNail> homePageDisplayThumbNail()
	{
		return homePageData.homePageContentThumbNail();
	}

}
