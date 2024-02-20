package com.soloproject1.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.content.dto.ContentDetailDTO;
import com.soloproject1.mypage.bo.MyPageBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/mypage")
@RestController
public class MyPageRestController {

	@Autowired
	private MyPageBO myPageBO;
	
	@GetMapping("/favorite-list")
	public List<ContentDetailDTO> favoriteList(HttpSession session) {
		int userId = (int) session.getAttribute("userId");
		return myPageBO.getFavoriteListByUserId(userId);
	}

}
