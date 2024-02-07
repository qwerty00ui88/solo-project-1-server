package com.soloproject1.recommend.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.recommend.domain.Recommend;
import com.soloproject1.recommend.mapper.RecommendMapper;

@Service
public class RecommendBO {

	@Autowired
	private RecommendMapper recommendMapper;

	public int addRecommend(int userId, int contentId, String status) {
		return recommendMapper.insertRecommend(userId, contentId, status);
	}

	public Recommend getRecommendByUserIdAndContentId(int userId, int contentId) {
		return recommendMapper.selectRecommendByUserIdAndContentId(userId, contentId);
	}

	public int updateRecommendByUserIdAndContentId(int userId, int contentId, String status) {
		return recommendMapper.updateRecommendByUserIdAndContentId(userId, contentId, status);
	}

	public int deleteRecommendByUserIdAndContentId(int userId, int contentId) {
		return recommendMapper.deleteRecommendByUserIdAndContentId(userId, contentId);
	}

}
