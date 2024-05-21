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
import com.soloproject1.tmdb.bo.TmdbBO;
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
	
	@Autowired
	private TmdbBO tmdbBO;

	public DetailPageDTO generateDetailPageDTO(Integer userId, String mediaType, int tmdbId) {

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		ContentDetailDTO contentDetail = tmdbBO.getContentDetail(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId, contentDetail.getTitle(), contentDetail.getOriginalTitle(), contentDetail.getPosterPath(), contentDetail.getBackdropPath());
		} else {
			contentId = content.getId();
		}

		DetailPageDTO detailPage = new DetailPageDTO();

		// 컨텐츠 상세 정보
		detailPage.setContentDetail(contentDetail);
		
		// 사용자의 추천, 인생 컨텐츠, 코멘트 작성 상태
		if (userId != null) {
			Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);
			if (recommend != null) {
				detailPage.setRecommendStatus(recommend.getStatus());
			}
			detailPage.setFavorite(favoriteBO.getFavoriteByContentIdUserId(contentId, userId) != null);
			detailPage.setMyComment(commentBO.getCommentByContentIdUserId(contentId, userId));
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

		detailPage.setGoodCommentViewList(goodCommentViewList);
		detailPage.setBadCommentViewList(badCommentViewList);
		detailPage.setUnratedCommentViewList(unratedCommentViewList);

		return detailPage;
	};

}
