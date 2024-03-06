package com.soloproject1.mypage.domain;

import com.soloproject1.comment.entity.CommentEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class MyComment extends MyRecommend {
	private CommentEntity comment;
}
