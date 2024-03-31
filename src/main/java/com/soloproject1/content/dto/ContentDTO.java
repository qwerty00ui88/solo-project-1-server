package com.soloproject1.content.dto;

import java.util.Map;

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
	
	public ContentDTO(Map<String, Object> result) {
		this.setTmdbId((int) result.get("id"));
		this.setMediaType((String) result.get("media_type"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		this.setPopularity((Double) result.get("popularity"));
		this.setVoteAverage((Double) result.get("vote_average"));
		this.setVoteCount((Integer) result.get("vote_count"));
	}
	
	public static ContentDTO createDTO(Map<String, Object> result) {
		if (result.get("media_type").equals("movie")) {
            return new MovieDTO(result);
        } else {
            return new TVDTO(result);
        }
	}
}
