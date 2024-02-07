package com.soloproject1.recommend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;

import jakarta.servlet.http.HttpSession;

@RestController
public class RecommendRestController {

	@Autowired
	private RecommendBO recommendBO;

	@Autowired
	private ContentBO contentBO;

	@GetMapping("/recommend")
	public Map<String, Object> recommend(
			@RequestParam("mediaType") String mediaType,
			@RequestParam("tmdbId") int tmdbId, 
			@RequestParam("status") String status,
			HttpSession session) {

		int userId = (int)session.getAttribute("userId");

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId);
		} else {
			contentId = content.getId();
		}

		Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);

		Map<String, Object> result = new HashMap<>();
		if (recommend == null) {
			// 없으면 => status로 추가
			recommendBO.addRecommend(userId, contentId, status);
			result.put("code", 200);
			result.put("result", status);
		} else if (status.equals(recommend.getStatus()) == false) {
			// 있고 && status와 다르면 => status로 변경
			recommendBO.updateRecommendByUserIdAndContentId(userId, contentId, status);
			result.put("code", 200);
			result.put("result", status);
		} else if (status.equals(recommend.getStatus()) == true) {
			// 있고 && status와 같으면 => 삭제
			recommendBO.deleteRecommendByUserIdAndContentId(userId, contentId);
			result.put("code", 200);
			result.put("result", null);
		} else {
			result.put("code", 500);
			result.put("error_message", "추천 상태 추가에 실패했습니다. 관리자에게 문의하세요.");
		}

		return result;
	}

}
