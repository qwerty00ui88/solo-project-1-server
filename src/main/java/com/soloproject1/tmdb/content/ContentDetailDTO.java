package com.soloproject1.tmdb.content;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode(callSuper=true)
public class ContentDetailDTO extends ContentDTO {
	private Double popularity;

	private Double voteAverage;

	private Integer voteCount;
	
	private String releaseDate;
	
	private String overview;
	
	private ArrayList genres;
	
	private Integer runtime;
	
	private String tagline;
	
	private LinkedHashMap credits;
	
	public ContentDetailDTO(Map<String, Object> result) {
		super(result);
		this.setPopularity((Double) result.get("popularity"));
		this.setVoteAverage((Double) result.get("vote_average"));
		this.setVoteCount((Integer) result.get("vote_count"));
		this.setOverview((String) result.get("overview"));
		this.setGenres((ArrayList) result.get("genres"));
		this.setCredits((LinkedHashMap) result.get("credits"));
		if(result.get("media_type").equals("movie")) {
			this.setReleaseDate((String) result.get("release_date"));
			this.setRuntime((Integer) result.get("runtime"));
			this.setTagline((String) result.get("tagline"));
		} else if(result.get("media_type").equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
			this.setReleaseDate((String) result.get("first_air_date"));
		}
	}
	
	public ContentDetailDTO(Map<String, Object> result, String mediaType) {
		super(result, mediaType);
		this.setPopularity((Double) result.get("popularity"));
		this.setVoteAverage((Double) result.get("vote_average"));
		this.setVoteCount((Integer) result.get("vote_count"));
		this.setOverview((String) result.get("overview"));
		this.setGenres((ArrayList) result.get("genres"));
		this.setCredits((LinkedHashMap) result.get("credits"));
		if(mediaType.equals("movie")) {
			this.setReleaseDate((String) result.get("release_date"));
			this.setRuntime((Integer) result.get("runtime"));
			this.setTagline((String) result.get("tagline"));
		} else if(mediaType.equals("tv")) {
			this.setReleaseDate((String) result.get("first_air_date"));
		}
	}
}
