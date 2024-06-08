package com.soloproject1.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.detail.bo.DetailBO;
import com.soloproject1.detail.dto.DetailDTO;

import jakarta.servlet.http.HttpSession;

@RestController
public class DetailRestController {
	
	@Autowired
	private DetailBO detailBO;
	
	@GetMapping("/detail/{mediaType}/{tmdbId}")
	public DetailDTO detailView(
			@PathVariable("mediaType") String mediaType,
			@PathVariable("tmdbId") int tmdbId,
			HttpSession session) {

		Integer userId = (Integer)session.getAttribute("userId");
		
		return detailBO.generateDetailPageDTO(userId, mediaType, tmdbId);
		
	};
	
}
