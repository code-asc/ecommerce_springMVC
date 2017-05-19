package com.app.service;

import java.util.List;

import com.app.model.HomePageLargeImage;
import com.app.model.HomePageThumbNail;

public interface HomePageInfo {

	public abstract List<HomePageLargeImage> homePageDisplay();

	public abstract List<HomePageThumbNail> homePageDisplayThumbNail();

}