package com.soloproject1.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.detail.bo.DetailBO;
import com.soloproject1.detail.domain.DetailView;

import jakarta.servlet.http.HttpSession;


@RestController
public class DetailRestController {
	
	@Autowired
	private DetailBO detailBO;
	
	@RequestMapping("/detail")
	public DetailView detailView(
			@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId,
			HttpSession session) {

		Integer userId = (Integer)session.getAttribute("userId");
		
		return detailBO.generateDetailViewList(userId, mediaType, tmdbId);
		
	};
	
}
