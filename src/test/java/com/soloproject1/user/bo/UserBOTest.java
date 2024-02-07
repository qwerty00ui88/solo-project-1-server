package com.soloproject1.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.soloproject1.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
//	@Transactional
//	@Test
//	void 회원가입테스트() {
//		
//		User user = new User();
//		user.setName("홍길동");
//		user.setNickname("홍홍");
//		user.setEmail("hong@gmail.com");
//		user.setEmailAuthentication(true);
//		user.setPassword("1234");
//		user.setBirth(1887);
//		user.setGender("male");
//		
//		userBO.addUser(user);
//	}
	
	@Transactional
	@Test
	void 로그인테스트() {
		userBO.getUserByNicknamePassword("가나다", "a95bc16631ae2b6fadb455ee018da0adc2703e56d89e3eed074ce56d2f7b1b6a");
	}

}
