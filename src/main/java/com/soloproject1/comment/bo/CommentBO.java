package com.soloproject1.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.comment.repository.CommentRepository;

@Service
public class CommentBO {

	@Autowired
	private CommentRepository commentRepository;

	public CommentEntity addComment(int userId, int contentId, String text) {

		CommentEntity comment = CommentEntity.builder().userId(userId).contentId(contentId).text(text).build();

		return commentRepository.save(comment);
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
		
		for(CommentEntity comment : commentList) {
			CommentView commentView = new CommentView();
			
			// @@@ 회원 정보 삽입
			
			// 댓글 삽입
			commentView.setComment(comment);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}

}
