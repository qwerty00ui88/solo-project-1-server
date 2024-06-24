package com.soloproject1.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.common.EncryptUtils;
import com.soloproject1.emailVerification.bo.EmailVerificationBO;
import com.soloproject1.user.bo.UserBO;
import com.soloproject1.user.domain.User;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;

	@Autowired
	private EmailVerificationBO emailVerificationBO;

	private static final String LOGIN_URL = "https://goodorbad.site/login";
	
	private static final String ERROR_URL = "https://goodorbad.site/error";
	
	@PostMapping("/create")
	public Map<String, Object> create(@RequestBody User user) {
		Integer userId = userBO.addUser(user);
		Map<String, Object> result = new HashMap<>();
		if (userId != null) {
			result.put("code", 200);
			result.put("result", "성공");
			result.put("userId", userId);
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

		User user = userBO.getUserByNicknamePassword(nickname, hashedPassword);

		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("userNickname", user.getNickname());
			
			Map<String, Object> userInfo = new HashMap<>();
			userInfo.put("userId", user.getId());
			userInfo.put("userNickname",user.getNickname());
			
			result.put("code", 200);
			result.put("result", userInfo);
		} else {
			result.put("code", 500);
			result.put("error_message", "로그인에 실패했습니다. 관리자에게 문의하세요.");
		}

		return result;

	}

	@GetMapping("/logout")
	public Map<String, Object> logout(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userNickname");

		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");

		return result;
	}
	
	@GetMapping("/checkLoginStatus")
	public Map<String, Object> checkLoginStatus(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		String userNickname = (String) session.getAttribute("userNickname");
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		if(userId != null && userNickname != null) {
			Map<String, Object> userInfo = new HashMap<>();
			userInfo.put("userId", userId);
			userInfo.put("userNickname",userNickname);
			result.put("result", userInfo);
		} else {
			result.put("result", null);
		}
		return result;
	}

	@GetMapping("/isDuplicated")
	public Map<String, Object> isDuplicated(@RequestParam("nickname") String nickname) {
		Boolean isDuplicated =  userBO.getUserByNickname(nickname) != null;
		
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", isDuplicated);
	
		return result;
	}

	@GetMapping("/verify-email")
	public ResponseEntity<Void> verifyEmail(@RequestParam("userId") int userId, @RequestParam("token") String token) {
		boolean isVerified = userBO.verifyEmail(userId, token);
		if (isVerified) {
			return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", LOGIN_URL)
	                .build();	
		} else {
			return ResponseEntity.status(HttpStatus.FOUND)
	                .header("Location", ERROR_URL)
	                .build();
		}
	}

	@PostMapping("/resend-verification-email")
	public Map<String, Object> resendVerificationEmail(@RequestBody Map<String, Object> request) {
		Integer userId = (Integer) request.get("userId");
		String purpose = (String) request.get("purpose");

		User user = userBO.getUserByUserId(userId);

		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			String token = emailVerificationBO.addToken(userId, purpose);

			String subject = "인증 메일입니다.";
			String text = "http://localhost/user/verify-email?userId=" + userId + "&token=" + token;
			emailVerificationBO.sendEmail(user.getEmail(), subject, text);

			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "이메일 인증에 실패하였습니다. 다시 시도해주세요.");
		}

		return result;
	}

}
