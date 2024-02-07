package com.soloproject1.comment.domain;

import com.soloproject1.comment.entity.CommentEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CommentView {
	
	private int userId;
	
	private String nickname;
	
	private CommentEntity comment;
	
}
