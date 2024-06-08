package com.soloproject1.tmdb.dto;

import java.util.Map;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TmdbContentDTO {
	private String mediaType;

	private int tmdbId;

	private String title;

	private String originalTitle;

	private String posterPath;

	private String backdropPath;
	
	private Double popularity;

	public TmdbContentDTO(Map<String, Object> result) {
		this.setMediaType((String) result.get("media_type"));
		this.setTmdbId((int) result.get("id"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		this.setPopularity((Double) result.get("popularity"));
		if(result.get("media_type").equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if(result.get("media_type").equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}
	}

	public TmdbContentDTO(Map<String, Object> result, String mediaType) {
		String type = mediaType.equals("all") ? (String) result.get("media_type") : mediaType;
		
		this.setMediaType(type);
		this.setTmdbId((int) result.get("id"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		this.setPopularity((Double) result.get("popularity"));
		if (type.equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if (type.equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}

	}
}
