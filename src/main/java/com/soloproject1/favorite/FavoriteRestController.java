package com.soloproject1.favorite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.favorite.domain.Favorite;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/favorite")
@RestController
public class FavoriteRestController {

	@Autowired
	private FavoriteBO favoriteBO;

	@GetMapping("/toggle")
	public Map<String, Object> favoriteToggle(@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId, HttpSession session) {

		int userId = (int) session.getAttribute("userId");

		favoriteBO.favoriteToggle(userId, mediaType, tmdbId);

		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}

}
