package com.soloproject1.common;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class EncryptUtilsTest {
	
	@Test
	void sha256해싱테스트() {
		EncryptUtils.sha256("안녕");
	}

}
