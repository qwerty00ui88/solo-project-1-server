package com.soloproject1.comment.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.comment.repository.CommentRepository;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.recommend.bo.RecommendBO;
import com.soloproject1.recommend.domain.Recommend;
import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;
import com.soloproject1.user.bo.UserBO;

@Service
public class CommentBO {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ContentBO contentBO;

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private TmdbBO tmdbBO;
	
	@Autowired
	private RecommendBO recommendBO;
	
	public Map<String, Object> addComment(int userId, String mediaType, int tmdbId, String text) {

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		TmdbContentDetailDTO contentDetailDTO = tmdbBO.getContentDetail(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId, contentDetailDTO.getTitle(), contentDetailDTO.getOriginalTitle(), contentDetailDTO.getPosterPath(), contentDetailDTO.getBackdropPath());
		} else {
			contentId = content.getId();
		}
		
		Map<String, Object> result = new HashMap<>();
		CommentEntity comment = commentRepository.findByContentIdAndUserId(contentId, userId);
		if(comment != null) {
			result.put("code", 500);
			result.put("error_message", "작성한 코멘트가 존재합니다.");
			return result;
		}
		
		CommentEntity newComment = commentRepository.save(CommentEntity.builder().userId(userId).contentId(contentId).text(text).build());
		result.put("code", 200);
		result.put("result", newComment);
		return result;
	}

	public CommentEntity updateCommentById(int commentId, String text) {
		
		CommentEntity comment = commentRepository.findById(commentId).orElse(null);

		if (comment != null) {
			comment = comment.toBuilder().text(text).build();
			comment = commentRepository.save(comment);
		}

		return comment;
	}

	public void deleteCommentById(int commentId) {
		
		CommentEntity comment = commentRepository.findById(commentId).orElse(null);

		if (comment != null) {
			commentRepository.delete(comment);
		}

	}

	public List<CommentView> generateCommentViewListByContentId(int contentId) {
		List<CommentView> commentViewList = new ArrayList<>();

		List<CommentEntity> commentList = commentRepository.findByContentIdOrderByUpdatedAtDesc(contentId);

		for (CommentEntity comment : commentList) {
			CommentView commentView = new CommentView();

			int userId = comment.getUserId();
			
			// userId 세팅
			commentView.setUserId(userId);
			
			// nickname 세팅
			commentView.setNickname(userBO.getUserByUserId(userId).getNickname());
			
			// 코멘트 리스트 세팅
			commentView.setComment(comment);
			
			// 추천 상태 세팅
			Recommend recommend = recommendBO.getRecommendByUserIdAndContentId(userId, contentId);
			if(recommend != null) commentView.setRecommendStatus(recommend.getStatus());
			
			commentViewList.add(commentView);
		}

		return commentViewList;
	}
	
	public List<CommentEntity> getcommentListByUserIdOrderByUpdatedAtDesc(int userId) {
		return commentRepository.findByUserIdOrderByUpdatedAtDesc(userId);
	}
	
	public CommentEntity getCommentByContentIdUserId(int contentId, int userId) {
		return commentRepository.findByContentIdAndUserId(contentId, userId);
	}

}
