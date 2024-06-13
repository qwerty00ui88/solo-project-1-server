package com.soloproject1.recommend.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class RecommendBOTest {

	@Autowired
	RecommendBO recommendBO;

	// @Transactional
	@Test
	void 추천추가() {
		recommendBO.addRecommend(1, 13, "good");
	}

	// @Transactional
	@Test
	void 추천조회() {
		recommendBO.getRecommendByUserIdAndContentId(1, 13);
	}

	// @Transactional
	@Test
	void 추천수정() {
		recommendBO.updateRecommendByUserIdAndContentId(1, 13, "bad");
	}

	// @Transactional
	@Test
	void 추천삭제() {
		recommendBO.deleteRecommendByUserIdAndContentId(1, 13);
	}

}
