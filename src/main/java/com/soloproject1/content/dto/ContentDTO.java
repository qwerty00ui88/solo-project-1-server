package com.soloproject1.content.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDTO {
	@JsonProperty("id")
	private int tmdbId;

	private String mediaType;

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
