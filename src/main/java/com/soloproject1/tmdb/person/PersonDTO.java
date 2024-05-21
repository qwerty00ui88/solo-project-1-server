package com.soloproject1.tmdb.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PersonDTO {
	@JsonProperty("id")
	private int personId;
	
	private String name;
	
	@JsonProperty("profile_path")
	private String profilePath;
}
