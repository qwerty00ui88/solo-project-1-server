package com.soloproject1.detail.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.detail.domain.DetailView;
import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;

@Service
public class DetailBO {
	
	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	@Autowired
	private FavoriteBO favoriteBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public DetailView generateDetailViewList(String mediaType, int tmdbId) {
		
		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId);
		} else {
			contentId = content.getId();
		}
		
		DetailView detailView = new DetailView();
		
		int userId = 1; // @@@ 추후 세션으로 변경 예정
		
		// 추천 상태
		Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);
		if(recommend != null) {
			detailView.setRecommendStatus(recommend.getStatus());
		}
		
		
		// 인생 컨텐츠 등록 여부
		detailView.setFavorite(favoriteBO.getFavoriteByContentIdUserId(contentId, userId) == null);
		
		// 코멘트
		List<CommentView> commentViewList = commentBO.generateCommentViewListByContentId(contentId);
		List<CommentView> goodCommentViewList = new ArrayList<>();
		List<CommentView> badCommentViewList = new ArrayList<>();
		List<CommentView> unratedCommentViewList = new ArrayList<>();
		
		for(CommentView commentView : commentViewList) {
			int commentUserId = 1; // @@@ 코멘트 작성자 userId
			Recommend commentRecommend = recommendBO.getRecommendByUserIdAndContentId(commentUserId, contentId);
			if(commentRecommend == null) {
				unratedCommentViewList.add(commentView);
			} else if(commentRecommend.getStatus().equals("good")) {
				goodCommentViewList.add(commentView);
			} else if(commentRecommend.getStatus().equals("bad")) {
				badCommentViewList.add(commentView);
			}
		}
		
		detailView.setGoodCommentViewList(goodCommentViewList);
		detailView.setBadCommentViewList(badCommentViewList);
		detailView.setUnratedCommentViewList(unratedCommentViewList);
		
		return detailView;
	};
	
}
