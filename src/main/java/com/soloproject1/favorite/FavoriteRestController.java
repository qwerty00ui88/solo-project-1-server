package com.soloproject1.favorite;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.favorite.bo.FavoriteBO;

@RestController
public class FavoriteRestController {

	@Autowired
	private FavoriteBO favoriteBO;
	
	@RequestMapping("/favorite")
	public Map<String, Object> favoriteToggle(
			@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId) {
		
		int userId = 1; // @@@ 추후 세션에서 가져올 예정
		
		favoriteBO.favoriteToggle(userId, mediaType, tmdbId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}
