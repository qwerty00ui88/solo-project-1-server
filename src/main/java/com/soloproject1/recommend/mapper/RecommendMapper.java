package com.soloproject1.recommend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.soloproject1.recommend.domain.Recommend;

@Mapper
public interface RecommendMapper {

	public int insertRecommend(
			@Param("userId") int userId, 
			@Param("contentId") int contentId, 
			@Param("status") String status);
	
	public Recommend selectRecommendByUserIdAndContentId(
			@Param("userId") int userId, 
			@Param("contentId") int contentId);
	
	public int updateRecommendByUserIdAndContentId(
			@Param("userId") int userId, 
			@Param("contentId") int contentId, 
			@Param("status") String status);
	
	public int deleteRecommendByUserIdAndContentId(
			@Param("userId") int userId, 
			@Param("contentId") int contentId);
	
}
