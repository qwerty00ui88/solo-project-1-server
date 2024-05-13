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

	private String releaseDate;
	
	private String overview;
	
	private ArrayList genres;
	
	private Integer runtime;
	
	private LinkedHashMap credits;
	
	public ContentDetailDTO(Map<String, Object> result) {
		super(result);
		this.setOverview((String) result.get("overview"));
		this.setGenres((ArrayList) result.get("genres"));
		this.setCredits((LinkedHashMap) result.get("credits"));
		if(result.get("media_type").equals("movie")) {
			this.setReleaseDate((String) result.get("release_date"));
			this.setRuntime((Integer) result.get("runtime"));
		} else if(result.get("media_type").equals("tv")) {
			this.setReleaseDate((String) result.get("first_air_date"));
		}
	}
	
	public ContentDetailDTO(Map<String, Object> result, String mediaType) {
		super(result, mediaType);
		this.setOverview((String) result.get("overview"));
		this.setGenres((ArrayList) result.get("genres"));
		this.setCredits((LinkedHashMap) result.get("credits"));
		if(mediaType.equals("movie")) {
			this.setReleaseDate((String) result.get("release_date"));
			this.setRuntime((Integer) result.get("runtime"));
		} else if(mediaType.equals("tv")) {
			this.setReleaseDate((String) result.get("first_air_date"));
		}
	}
}
