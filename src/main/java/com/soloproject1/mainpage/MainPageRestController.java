package com.soloproject1.mainpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.mainpage.bo.MainPageBO;
import com.soloproject1.mainpage.domain.MainPageView;

@RequestMapping("/mainpage")
@RestController
public class MainPageRestController {

	@Autowired
	private MainPageBO mainPageBO;
	
	@GetMapping("/")
	public MainPageView mainPageView() {
		return mainPageBO.generateMainPageView();
	}
	
}
