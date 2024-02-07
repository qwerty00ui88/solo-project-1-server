package com.soloproject1.detail.domain;

import java.util.List;

import com.soloproject1.comment.domain.CommentView;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DetailView {
	
	private String recommendStatus;
	private boolean isFavorite;
	
	private List<CommentView> goodCommentViewList;
	private List<CommentView> badCommentViewList;
	private List<CommentView> unratedCommentViewList;
	
}
