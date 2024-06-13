package com.soloproject1.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

@RequestMapping("/content")
@RestController
public class ContentRestController {

	@Autowired
	private ContentBO contentBO;
	
	@GetMapping("/{mediaType}/{tmdbId}")
	public TmdbContentDetailDTO getContentDetailDTOByMediaTypeId(@PathVariable("mediaType") String mediaType, @PathVariable("tmdbId") int tmdbId) {
		return contentBO.getContentDetailById(mediaType, tmdbId);
	}
	
}
