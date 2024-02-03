package com.soloproject1.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.detail.bo.DetailBO;
import com.soloproject1.detail.domain.DetailView;


@RestController
public class DetailRestController {
	
	@Autowired
	private DetailBO detailBO;
	
	@RequestMapping("/detail")
	public DetailView detailView(
			@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId) {

		return detailBO.generateDetailViewList(mediaType, tmdbId);
		
	};
	
}
