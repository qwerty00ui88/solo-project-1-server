package com.soloproject1.detail.dto;

import java.util.List;

import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class DetailDTO {
	
	private TmdbContentDetailDTO contentDetail;
	
	private String recommendStatus;
	private boolean isFavorite;
	private CommentEntity myComment;
	
	private List<CommentView> goodCommentViewList;
	private List<CommentView> badCommentViewList;
	private List<CommentView> unratedCommentViewList;
	
}
