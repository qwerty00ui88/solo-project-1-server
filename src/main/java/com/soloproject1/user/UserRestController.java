package com.soloproject1.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.common.EncryptUtils;
import com.soloproject1.user.bo.UserBO;
import com.soloproject1.user.domain.User;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;

	@PostMapping("/create")
	public Map<String, Object> create(@RequestBody User user) {
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.sha256(user.getPassword());
		user.setPassword(hashedPassword);

		// db insert
		userBO.addUser(user);
		Integer userId = user.getId();

		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다. 관리자에게 문의하세요.");
		}
		return result;
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, Object> request, HttpServletResponse response,
			HttpSession session) {
		String nickname = (String) request.get("nickname");
		String hashedPassword = EncryptUtils.sha256((String) request.get("password"));

		// db select
		User user = userBO.getUserByNicknamePassword(nickname, hashedPassword);

		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			session.setAttribute("userId", user.getId());

			Cookie sessionCookie = new Cookie("userId", session.getId());
			sessionCookie.setPath("/");
			response.addCookie(sessionCookie);

			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다. 관리자에게 문의하세요.");
		}

		return result;

	}

	@GetMapping("/logout")
	public Map<String, Object> logout(HttpSession session) {
		session.removeAttribute("userId");

		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");

		return result;
	}

}
