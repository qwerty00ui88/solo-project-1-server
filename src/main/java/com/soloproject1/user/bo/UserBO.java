package com.soloproject1.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.soloproject1.common.EncryptUtils;
import com.soloproject1.emailVerification.bo.EmailVerificationBO;
import com.soloproject1.user.domain.User;
import com.soloproject1.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private EmailVerificationBO emailVerificationBO;
	
	@Value("${emailVerification.baseUrl}")
	private String emailVerificationBaseUrl;
	
	public Integer addUser(User user) {
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.sha256(user.getPassword());
		user.setPassword(hashedPassword);
		
		// db insert
		userMapper.insertUser(user);
		
		// 이메일 인증
		Integer userId = user.getId();
		if(userId != null) {
			User insertedUser = userMapper.selectUserByUserId(userId);
			String token = emailVerificationBO.addToken(insertedUser.getId(), "회원가입");
			
			String subject = "인증 메일입니다.";
			String text = emailVerificationBaseUrl + "/user/verify-email?userId=" + userId + "&token=" + token;
			emailVerificationBO.sendEmail(insertedUser.getEmail(), subject, text);
		}
		return userId;
	}
	
	public User getUserByNicknamePassword(String nickname, String password) {
		return userMapper.selectUserByNicknamePassword(nickname, password);
	}
	
	public User getUserByUserId(int id) {
		return userMapper.selectUserByUserId(id);
	}
	
	public User getUserByNickname(String nickname) {
		return userMapper.selectUserByNickname(nickname);
	}
	
	public boolean verifyEmail(int userId, String token) {
		boolean isVerified = emailVerificationBO.verifyEmail(userId, token);
		if(isVerified) {
			userMapper.updateEmailVerifiedByUserId(userId, true);
		} else {
			userMapper.updateEmailVerifiedByUserId(userId, false);
		}
		return isVerified;
	}
	
}
