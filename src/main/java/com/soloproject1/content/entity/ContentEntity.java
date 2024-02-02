package com.soloproject1.content.entity;

import java.time.ZonedDateTime;
import java.util.Date;

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

	@Column(name = "tmdbId")
	private int tmdbId;

	@Column(name = "mediaType")
	private String mediaType;

	private String title;

	@Column(name = "originalTitle")
	private String originalTitle;

	@Column(name = "releaseDate")
	private Date releaseDate;

	@Column(name = "posterPath")
	private String posterPath;

	@Column(name = "backdropPath")
	private String backdropPath;

	private Double popularity;

	@Column(name = "voteAverage")
	private Double voteAverage;

	@Column(name = "voteCount")
	private Integer voteCount;
	
	@UpdateTimestamp
	@Column(name = "createdAt", updatable = false)
	private ZonedDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "updatedAt")
	private ZonedDateTime updatedAt;
}
