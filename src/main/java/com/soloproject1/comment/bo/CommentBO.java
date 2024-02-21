package com.soloproject1.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.comment.repository.CommentRepository;
import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.user.bo.UserBO;

@Service
public class CommentBO {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ContentBO contentBO;

	@Autowired
	private UserBO userBO;
	
	public CommentEntity addComment(int userId, String mediaType, int tmdbId, String text) {

		Integer contentId = null;
		ContentEntity content = contentBO.getContentByMediaTypeAndTmdbId(mediaType, tmdbId);
		if (content == null) {
			contentId = contentBO.addContent(mediaType, tmdbId);
		} else {
			contentId = content.getId();
		}

		return commentRepository.save(CommentEntity.builder().userId(userId).contentId(contentId).text(text).build());
	
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

		List<CommentEntity> commentList = commentRepository.findByContentId(contentId);

		for (CommentEntity comment : commentList) {
			CommentView commentView = new CommentView();

			int userId = comment.getUserId();
			
			// userId 세팅
			commentView.setUserId(userId);
			
			// nickname 세팅
			commentView.setNickname(userBO.getUserByUserId(userId).getNickname());
			
			// 코멘트 리스트 세팅
			commentView.setComment(comment);
			
			commentViewList.add(commentView);
		}

		return commentViewList;
	}
	
	public List<CommentEntity> getcommentListByUserId(int userId) {
		return commentRepository.findByUserId(userId);
	}

}
