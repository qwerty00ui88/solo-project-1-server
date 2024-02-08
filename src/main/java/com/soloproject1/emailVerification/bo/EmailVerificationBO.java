package com.soloproject1.emailVerification.bo;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.soloproject1.emailVerification.entity.EmailVerificationEntity;
import com.soloproject1.emailVerification.repository.EmailVerificationRepository;

@Service
public class EmailVerificationBO {
	@Autowired
	private EmailVerificationRepository emailVerificationRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	public String addToken(int userId, String purpose) {
		String token = UUID.randomUUID().toString();
		EmailVerificationEntity emailVerification = EmailVerificationEntity.builder()
				.userId(userId)
				.token(token)
				.purpose(purpose)
				.build();
		emailVerificationRepository.save(emailVerification);
		return token;
	}

	public void sendEmail(String email, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}

	public boolean verifyEmail(int userId, String token) {

		// db select
		EmailVerificationEntity emailVerification = emailVerificationRepository.findByUserIdAndToken(userId, token);

		// 일치하는 토큰이 없는 경우 => 실패
		if(emailVerification == null) {
			return false;
		}
		
		// 토큰이 만료된 경우 => 실패
		ZonedDateTime expiredAt = emailVerification.getExpiredAt();
		ZonedDateTime currentTime = ZonedDateTime.now();
		if (expiredAt.isBefore(currentTime)) {
			emailVerificationRepository.delete(emailVerification);
			return false;
		}

		// 성공
		emailVerificationRepository.delete(emailVerification);
		return true;
	}
	
}
