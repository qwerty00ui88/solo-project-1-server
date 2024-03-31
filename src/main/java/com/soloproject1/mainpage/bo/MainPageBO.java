package com.soloproject1.mainpage.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.mainpage.domain.MainPageView;

@Service
public class MainPageBO {
	
	@Autowired
	private ContentBO contentBO;
	
	public MainPageView generateMainPageView() {
		MainPageView mainPageView = new MainPageView();
		mainPageView.setAllTrending(contentBO.getAllTrendingList());
		return mainPageView;
	}
	
}
