package com.soloproject1.tmdb.content;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDTO {
	@JsonProperty("id")
	private int tmdbId;

	@JsonProperty("media_type")
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
	
	private String title;
	
	private String originalTitle;
	
	public ContentDTO(Map<String, Object> result) {
		this.setTmdbId((int) result.get("id"));
		this.setMediaType((String) result.get("media_type"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		this.setPopularity((Double) result.get("popularity"));
		this.setVoteAverage((Double) result.get("vote_average"));
		this.setVoteCount((Integer) result.get("vote_count"));
		
		if(result.get("media_type").equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if(result.get("media_type").equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}
	}
	
	public ContentDTO(Map<String, Object> result, String mediaType) {
		this.setTmdbId((int) result.get("id"));
		this.setMediaType(mediaType);
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		this.setPopularity((Double) result.get("popularity"));
		this.setVoteAverage((Double) result.get("vote_average"));
		this.setVoteCount((Integer) result.get("vote_count"));
		
		if(mediaType.equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if(mediaType.equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}
	}

//	public static ContentDTO createDTO(Map<String, Object> result) {
//		if (result.get("media_type").equals("movie")) {
//			return new MovieDTO(result);
//		} else {
//			return new TVDTO(result);
//		}
//	}

}
