package com.soloproject1.detail.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.detail.dto.DetailDTO;
import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;
import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

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
	
	@Autowired
	private TmdbBO tmdbBO;

	public DetailDTO generateDetailPageDTO(Integer userId, String mediaType, int tmdbId) {

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		TmdbContentDetailDTO contentDetail = tmdbBO.getContentDetail(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId, contentDetail.getTitle(), contentDetail.getOriginalTitle(), contentDetail.getPosterPath(), contentDetail.getBackdropPath());
		} else {
			contentId = content.getId();
		}

		DetailDTO detail = new DetailDTO();

		// 컨텐츠 상세 정보
		detail.setContentDetail(contentDetail);
		
		// 사용자의 추천, 인생 컨텐츠, 코멘트 작성 상태
		if (userId != null) {
			Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);
			if (recommend != null) {
				detail.setRecommendStatus(recommend.getStatus());
			}
			detail.setFavorite(favoriteBO.getFavoriteByContentIdUserId(contentId, userId) != null);
			detail.setMyComment(commentBO.getCommentByContentIdUserId(contentId, userId));
		}
		
		// 전체 코멘트
		List<CommentView> commentViewList = commentBO.generateCommentViewListByContentId(contentId);
		detail.setCommentViewList(commentViewList);

		return detail;
	};

}
