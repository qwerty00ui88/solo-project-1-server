package com.soloproject1.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloproject1.comment.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	
	public List<CommentEntity> findByContentId(int contentId);
	
	public List<CommentEntity> findByUserId(int userId);
	
}
