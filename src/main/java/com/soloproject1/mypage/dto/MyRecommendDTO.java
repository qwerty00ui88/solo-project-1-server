package com.soloproject1.mypage.dto;

import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.recommend.domain.Recommend;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MyRecommendDTO {
	private ContentEntity contentEntity;
	private Recommend recommend;
}
