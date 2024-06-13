package com.soloproject1.favorite.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.favorite.domain.Favorite;
import com.soloproject1.favorite.mapper.FavoriteMapper;
import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

@Service
public class FavoriteBO {

	@Autowired
	private FavoriteMapper favoriteMapper;

	@Autowired
	private ContentBO contentBO;

	@Autowired
	private TmdbBO tmdbBO;
	
	public Favorite getFavoriteByContentIdUserId(int contentId, int userId) {
		return favoriteMapper.selectFavoriteByContentIdUserId(contentId, userId);
	}

	public List<Favorite> getFavoriteListByUserId(int userId) {
		return favoriteMapper.selectFavoriteListByUserId(userId);
	}
	
	public void favoriteToggle(int userId, String mediaType, int tmdbId) {
		
		// contentId 조회(없으면 생성)
		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		TmdbContentDetailDTO contentDetailDTO = tmdbBO.getContentDetail(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId, contentDetailDTO.getTitle(), contentDetailDTO.getOriginalTitle(), contentDetailDTO.getPosterPath(), contentDetailDTO.getBackdropPath());
		} else {
			contentId = content.getId();
		}
		
		// 인생 컨텐츠 조회
		List<Favorite> favoriteList = favoriteMapper.selectFavoriteListByUserId(userId);
		
		// 인생 컨텐츠에 이미 있는 경우
		for(Favorite favorite : favoriteList) {
			if(favorite.getContentId() == contentId) {
				favoriteMapper.deleteFavoriteByContentIdUserId(contentId, userId);
				return;
			} 
		}
		
		// 인생 컨텐츠에 없는 경우 -> 3개 미만일 경우에만 추가
		if(favoriteList.size() < 3) {
			favoriteMapper.insertFavorite(contentId, userId);
		}
	}

	public void deleteFavoriteByMediaTypeTmdbIdUserId(int userId, String mediaType, int tmdbId) {
		int contentId = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId).getId();
		favoriteMapper.deleteFavoriteByContentIdUserId(contentId, userId);
	}

}
