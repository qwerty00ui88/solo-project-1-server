package com.soloproject1.favorite.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.favorite.domain.Favorite;
import com.soloproject1.favorite.mapper.FavoriteMapper;

@Service
public class FavoriteBO {
	
	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Autowired
	private ContentBO contentBO;
	
	public Favorite getFavoriteByContentIdUserId (int contentId, int userId) {
		return favoriteMapper.selectFavoriteByContentIdUserId(contentId, userId);
	}
	
	public void favoriteToggle(int userId, String mediaType, int tmdbId) {
		
		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId);
		} else {
			contentId = content.getId();
		}
		
		// DB SELECT
		Favorite favorite = favoriteMapper.selectFavoriteByContentIdUserId(contentId, userId);
		
		// favorite이 있으면 추가 없으면 삭제
		if(favorite == null) {
			// DB insert
			favoriteMapper.insertFavorite(contentId, userId);
		} else {
			// DB delete
			favoriteMapper.deleteFavoriteByContentIdUserId(contentId, userId);
		}
	}
	
	
}
