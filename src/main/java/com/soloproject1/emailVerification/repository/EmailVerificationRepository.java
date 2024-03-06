package com.soloproject1.emailVerification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloproject1.emailVerification.entity.EmailVerificationEntity;

public interface EmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Integer> {
	
	public EmailVerificationEntity findByUserIdAndToken(int userId, String token);
	
	public void deleteByUserId(int userId);
	
}
