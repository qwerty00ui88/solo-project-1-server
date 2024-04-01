package com.soloproject1.mainpage.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.dto.ContentDTO;
import com.soloproject1.mainpage.domain.MainPageView;

@Service
public class MainPageBO {
	
	@Autowired
	private ContentBO contentBO;
	
	public MainPageView generateMainPageView() {
		MainPageView mainPageView = new MainPageView();
		
		List<ContentDTO> allTrendingList = contentBO.getTrendingList("all", "day");
		mainPageView.setAllTrending(allTrendingList);
		
		mainPageView.setMovieTrending(contentBO.getTrendingList("movie", "day"));
		
		mainPageView.setAllTrendingVideo(contentBO.getAllTrendingVideoList(allTrendingList));
		
		return mainPageView;
	}
	
}
