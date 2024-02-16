package com.soloproject1.common.domain;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDetail {
	private int tmdbId;
	private String mediaType;
	private String title;
	private String originalTitle;
	private Date releaseDate;
	private String posterPath;
	private String backdropPath;
	private Double popularity;
	private Double voteAverage;
	private Integer voteCount;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
