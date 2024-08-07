package com.soloproject1.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.mypage.bo.MyPageBO;
import com.soloproject1.mypage.dto.MyComment;
import com.soloproject1.mypage.dto.MyRecommendDTO;
import com.soloproject1.tmdb.dto.TmdbContentDTO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mypage")
@RestController
public class MyPageRestController {

	@Autowired
	private MyPageBO myPageBO;
	
	@GetMapping("/favorite-list")
	public List<TmdbContentDTO> favoriteList(HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		return myPageBO.getFavoriteListByUserId(userId);
	}
	
	@GetMapping("/recommend-list")
	public List<MyRecommendDTO> recommendList(HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		return myPageBO.getRecommendListByUserId(userId);
	}
	
	@GetMapping("/comment-list")
	public List<MyComment> commentList(HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		return myPageBO.getCommentListByUserId(userId);
	}

}
