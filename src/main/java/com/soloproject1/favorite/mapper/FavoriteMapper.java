package com.soloproject1.favorite.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.soloproject1.favorite.domain.Favorite;

@Mapper
public interface FavoriteMapper  {

	public void insertFavorite(
			@Param("contentId")int contentId, 
			@Param("userId")int userId);
	
	public Favorite selectFavoriteByContentIdUserId(
			@Param("contentId")int contentId, 
			@Param("userId")int userId);
	
	public void deleteFavoriteByContentIdUserId(
			@Param("contentId")int contentId, 
			@Param("userId")int userId);
	
	public List<Favorite> selectFavoriteListByUserId(int userId);
	
}
