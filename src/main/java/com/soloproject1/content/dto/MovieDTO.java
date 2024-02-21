package com.soloproject1.content.dto;

import java.util.Date;

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
	private Date releaseDate;
	
}
