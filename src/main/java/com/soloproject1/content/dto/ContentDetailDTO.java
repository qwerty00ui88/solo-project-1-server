package com.soloproject1.content.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDetailDTO {
	@JsonProperty("id")
	private int tmdbId;

	private String mediaType;

	private String title;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("release_date")
	private Date releaseDate;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	private Double popularity;

	@JsonProperty("vote_average")
	private Double voteAverage;

	@JsonProperty("vote_count")
	private Integer voteCount;
}
