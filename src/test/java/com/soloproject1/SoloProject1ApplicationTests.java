package com.soloproject1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootTest
class SoloProject1ApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Test
	void 더하기테스트() {
		log.info("$$$$$$$$$$ 더하기 테스트 시작");
		int a = 10;
		int b = 20;
		
		assertEquals(20, a + b);
	}

}
