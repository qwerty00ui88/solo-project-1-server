package com.soloproject1.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class PersonDTO {
	
	private Boolean adult;
	
	private int gender;
	
	@JsonProperty("id")
	private int personId;
	
	@JsonProperty("known_for_department")
	private String knownForDepartment;
	
	private String name;
	
	private Double popularity;
	
	@JsonProperty("profile_path")
	private String profilePath;
	
}
