package com.soloproject1.content.entity;

import java.time.ZonedDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "content")
@Entity
public class ContentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "mediaType")
	private String mediaType;
	
	@Column(name = "tmdbId")
	private int tmdbId;
	
	private String title;
	
	@Column(name = "originalTitle")
	private String originalTitle;
	
	@Column(name = "posterPath")
	private String posterPath;
	
	@Column(name = "backdropPath")
	private String backdropPath;
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
}
