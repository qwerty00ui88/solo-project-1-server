package com.soloproject1.mainpage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.mainpage.bo.MainPageBO;
import com.soloproject1.mainpage.dto.MainPageDTO;
import com.soloproject1.tmdb.content.ContentDTO;

@RequestMapping("/mainpage")
@RestController
public class MainPageRestController {

	@Autowired
	private MainPageBO mainPageBO;

	@Autowired
	private ContentBO contentBO;
	
	@GetMapping("/")
	public MainPageDTO mainPageView() {
		return mainPageBO.generateMainPageView();
	}
	
	@GetMapping("/trending")
	public List<ContentDTO> getTrending(@RequestParam("category") String category,
			@RequestParam("duration") String duration) {
		return contentBO.getTrendingList(category, duration);
	}

}
