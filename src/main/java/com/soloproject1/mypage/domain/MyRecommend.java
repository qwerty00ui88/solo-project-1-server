package com.soloproject1.mypage.domain;

import com.soloproject1.content.dto.ContentDTO;
import com.soloproject1.recommend.domain.Recommend;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MyRecommend {
	private ContentDTO contentDetail;
	private Recommend recommend;
}
