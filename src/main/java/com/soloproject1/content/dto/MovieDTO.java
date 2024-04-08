package com.soloproject1.content.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class MovieDTO extends ContentDTO {

	private String title;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("release_date")
	private String releaseDate;

	public MovieDTO(Map<String, Object> result) {
		super(result);
		this.setTitle((String) result.get("title"));
		this.setOriginalTitle((String) result.get("original_title"));
		this.setReleaseDate((String) result.get("release_date"));
	}

}
