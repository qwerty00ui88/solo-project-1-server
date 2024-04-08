package com.soloproject1.detailpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.detailpage.bo.DetailPageBO;
import com.soloproject1.detailpage.domain.DetailPage;

import jakarta.servlet.http.HttpSession;

@RestController
public class DetailPageRestController {
	
	@Autowired
	private DetailPageBO detailBO;
	
	@GetMapping("/detail")
	public DetailPage detailView(
			@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId,
			HttpSession session) {

		Integer userId = (Integer)session.getAttribute("userId");
		
		return detailBO.generateDetailViewList(userId, mediaType, tmdbId);
		
	};
	
}