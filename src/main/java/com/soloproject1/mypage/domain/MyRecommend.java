package com.soloproject1.mypage.domain;

import com.soloproject1.recommend.domain.Recommend;
import com.soloproject1.tmdb.content.ContentDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MyRecommend {
	private ContentDTO contentDetail;
	private Recommend recommend;
}
