package com.soloproject1.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soloproject1.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Test
	void test() {
		
		User user = new User();
		user.setName("홍길동");
		user.setNickname("홍홍");
		user.setEmail("hong@gmail.com");
		user.setEmailAuthentication(true);
		user.setPassword("1234");
		user.setBirth(1887);
		user.setGender("male");
		
		userBO.addUser(user);
	}

}
