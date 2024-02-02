package com.soloproject1.content.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soloproject1.content.entity.ContentEntity;

public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {

	public List<ContentEntity> findAll();
	
}
