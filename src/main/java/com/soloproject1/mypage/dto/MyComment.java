package com.soloproject1.mypage.dto;

import com.soloproject1.comment.entity.CommentEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class MyComment extends MyRecommendDTO {
	private CommentEntity comment;
}
