package com.soloproject1.detailpage.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.detailpage.dto.DetailPageDTO;
import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;
import com.soloproject1.tmdb.content.ContentDetailDTO;

@Service
public class DetailPageBO {

	@Autowired
	private ContentBO contentBO;

	@Autowired
	private RecommendBO recommendBO;

	@Autowired
	private FavoriteBO favoriteBO;

	@Autowired
	private CommentBO commentBO;

	public DetailPageDTO generateDetailPageDTO(Integer userId, String mediaType, int tmdbId) {

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId);
		} else {
			contentId = content.getId();
		}

		DetailPageDTO detailPageDTO = new DetailPageDTO();

		// 컨텐츠 상세 정보
		detailPageDTO.setContentDetailDTO(contentBO.getContentDetail(mediaType, tmdbId));
		
		// 사용자의 추천, 인생 컨텐츠, 코멘트 작성 상태
		if (userId != null) {
			Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);
			if (recommend != null) {
				detailPageDTO.setRecommendStatus(recommend.getStatus());
			}
			detailPageDTO.setFavorite(favoriteBO.getFavoriteByContentIdUserId(contentId, userId) != null);
			detailPageDTO.setMyComment(commentBO.getCommentByContentIdUserId(contentId, userId));
		}
		
		// 전체 코멘트
		List<CommentView> commentViewList = commentBO.generateCommentViewListByContentId(contentId);
		List<CommentView> goodCommentViewList = new ArrayList<>();
		List<CommentView> badCommentViewList = new ArrayList<>();
		List<CommentView> unratedCommentViewList = new ArrayList<>();

		for (CommentView commentView : commentViewList) {
			int commentUserId = commentView.getUserId();
			Recommend commentRecommend = recommendBO.getRecommendByUserIdAndContentId(commentUserId, contentId);
			if (commentRecommend == null) {
				unratedCommentViewList.add(commentView);
			} else if (commentRecommend.getStatus().equals("good")) {
				goodCommentViewList.add(commentView);
			} else if (commentRecommend.getStatus().equals("bad")) {
				badCommentViewList.add(commentView);
			}
		}

		detailPageDTO.setGoodCommentViewList(goodCommentViewList);
		detailPageDTO.setBadCommentViewList(badCommentViewList);
		detailPageDTO.setUnratedCommentViewList(unratedCommentViewList);

		return detailPageDTO;
	};

}
