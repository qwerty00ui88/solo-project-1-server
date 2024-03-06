package com.soloproject1.mypage.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.dto.ContentDTO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.favorite.bo.FavoriteBO;
import com.soloproject1.favorite.domain.Favorite;
import com.soloproject1.mypage.domain.MyComment;
import com.soloproject1.mypage.domain.MyRecommend;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;

import reactor.core.publisher.Mono;

@Service
public class MyPageBO {

	@Autowired
	private FavoriteBO favoriteBO;

	@Autowired
	private ContentBO contentBO;

	@Autowired
	private RecommendBO recommendBO;

	@Autowired
	private CommentBO commentBO;

	public List<ContentDTO> getFavoriteListByUserId(int userId) {

		// userId로 favoirte 리스트 가져오기
		List<Favorite> favoriteList = favoriteBO.getFavoriteListByUserId(userId);

		// 리스트를 순회하며 api 호출
		List<ContentDTO> contentDetailList = new ArrayList<>();
		for (Favorite favorite : favoriteList) {
			ContentEntity content = contentBO.getContentById(favorite.getContentId());
			ContentDTO contentDetail = contentBO.getContentDetail(content.getMediaType(), content.getTmdbId());
			contentDetailList.add(contentDetail);
		}
		return contentDetailList;
	}

	public List<MyRecommend> getRecommendListByUserId(int userId) {
		List<Recommend> recommendList = recommendBO.getRecommendListByUserId(userId);
		List<MyRecommend> myRecommendList = new ArrayList<>();
		for (Recommend recommend : recommendList) {
			MyRecommend myRecommend = new MyRecommend();

			ContentEntity content = contentBO.getContentById(recommend.getContentId());
			ContentDTO contentDetail = contentBO.getContentDetail(content.getMediaType(), content.getTmdbId());

			myRecommend.setContentDetail(contentDetail);
			myRecommend.setRecommend(recommend);

			myRecommendList.add(myRecommend);
		}
		return myRecommendList;
	}

	public List<MyComment> getCommentListByUserId(int userId) {
		List<CommentEntity> commentList = commentBO.getcommentListByUserIdOrderByUpdatedAtDesc(userId);
		List<MyComment> myCommentList = new ArrayList<>();
		for (CommentEntity comment : commentList) {
			MyComment myComment = new MyComment();

			ContentEntity content = contentBO.getContentById(comment.getContentId());
			ContentDTO contentDetail = contentBO.getContentDetail(content.getMediaType(), content.getTmdbId());
			Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, comment.getContentId());
			
			myComment.setContentDetail(contentDetail);
			myComment.setComment(comment);
			myComment.setRecommend(recommend);

			myCommentList.add(myComment);
		}
		return myCommentList;
	}

}
