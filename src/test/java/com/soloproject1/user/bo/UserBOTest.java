package com.soloproject1.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
//	@Test
//	void 회원가입테스트() {
//		
//		User user = new User();
//		user.setName("홍길동");
//		user.setNickname("홍홍");
//		user.setEmail("yirecav730@laymro.com");
//		user.setPassword("1234");
//		user.setBirth(1887);
//		user.setGender("male");
//		
//		userBO.addUser(user);
//	}
	
	@Test
	void 닉네임중복테스트() {
		userBO.getUserByNickname("a");
	}
	
	
//	@Transactional
//	@Test
//	void 로그인테스트() {
//		userBO.getUserByNicknamePassword("가나다", "a95bc16631ae2b6fadb455ee018da0adc2703e56d89e3eed074ce56d2f7b1b6a");
//	}

}
