package com.soloproject1.mainpage.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.mainpage.dto.MainPageDTO;
import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.content.ContentDTO;

@Service
public class MainPageBO {
	
	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private TmdbBO tmdbBO;
	
	public MainPageDTO generateMainPageView() {
		MainPageDTO mainPage = new MainPageDTO();
		
		List<ContentDTO> allTrendingList = tmdbBO.getTrendingList("all", "day");
		mainPage.setAllTrending(allTrendingList);
		mainPage.setMovieTrending(tmdbBO.getTrendingList("movie", "day"));
		mainPage.setAllTrendingVideo(tmdbBO.getAllTrendingVideoList(allTrendingList));
		
		return mainPage;
	}
	
}
