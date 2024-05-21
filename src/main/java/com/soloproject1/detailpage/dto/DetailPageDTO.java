package com.soloproject1.detailpage.dto;

import java.util.List;

import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.tmdb.content.ContentDetailDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DetailPageDTO {
	
	private ContentDetailDTO contentDetail;
	
	private String recommendStatus;
	private boolean isFavorite;
	private CommentEntity myComment;
	
	private List<CommentView> goodCommentViewList;
	private List<CommentView> badCommentViewList;
	private List<CommentView> unratedCommentViewList;
	
}
