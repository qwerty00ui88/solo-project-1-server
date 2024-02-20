package com.soloproject1.mypage.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.dto.ContentDetailDTO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.favorite.domain.Favorite;

@Service
public class MyPageBO {
	
	@Autowired
	private FavoriteBO favoriteBO;
	
	@Autowired
	private ContentBO contentBO;
	
	public List<ContentDetailDTO> getFavoriteListByUserId(int userId) {
		
		// userId로 favoirte 리스트 가져오기
		List<Favorite> favoriteList = favoriteBO.getFavoriteListByUserId(userId);
		
		// 리스트를 순회하며 api 호출
		List<ContentDetailDTO> contentDetailList = new ArrayList<>();
		for(Favorite favorite : favoriteList) {
			ContentEntity content = contentBO.getContentById(favorite.getContentId());
			ContentDetailDTO contentDetail = contentBO.getContentDetail(content.getMediaType(), content.getTmdbId());
			contentDetailList.add(contentDetail);
		}
		return contentDetailList;
	}
	
}
