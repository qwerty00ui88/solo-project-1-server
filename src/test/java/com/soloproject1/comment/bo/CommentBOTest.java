package com.soloproject1.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @SpringBootTest
class CommentBOTest {
	
	@Autowired
	CommentBO commentBO;
	
	@Transactional // rollback, 테이블이 많이 사용하는 BO 로직에는 사용해야 함
	@Test
	void 코멘트추가테스트() {
		log.info("########## 코멘트 추가 테스트 시작");
		commentBO.addComment(2, "tv", 45, "코멘트2222######");
	}
	
	@Test
	void 비어있는지확인() {
		// List<Integer> list = new ArrayList<>(); // []
		List<Integer> list = null; // []
		
		if(ObjectUtils.isEmpty(list)) {
			log.info("list is empty.");
		}
		
		// String str = null;
		String str = "";
		if(ObjectUtils.isEmpty(str)) {
			log.info("str is empty.");
		}
	}

	
}
