package com.soloproject1.tmdb.content;

import java.util.Map;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDTO {
	private String mediaType;

	private int tmdbId;

	private String title;

	private String originalTitle;

	private String posterPath;

	private String backdropPath;

	public ContentDTO(Map<String, Object> result) {
		this.setMediaType((String) result.get("media_type"));
		this.setTmdbId((int) result.get("id"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		if(result.get("media_type").equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if(result.get("media_type").equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}
	}

	public ContentDTO(Map<String, Object> result, String mediaType) {
		this.setMediaType(mediaType);
		this.setTmdbId((int) result.get("id"));
		this.setPosterPath((String) result.get("poster_path"));
		this.setBackdropPath((String) result.get("backdrop_path"));
		if (mediaType.equals("movie")) {
			this.setTitle((String) result.get("title"));
			this.setOriginalTitle((String) result.get("original_title"));
		} else if (mediaType.equals("tv")) {
			this.setTitle((String) result.get("name"));
			this.setOriginalTitle((String) result.get("original_name"));
		}

//	public static ContentDTO createDTO(Map<String, Object> result) {
//		if (result.get("media_type").equals("movie")) {
//			return new MovieDTO(result);
//		} else {
//			return new TVDTO(result);
//		}
//	}

	}
}
