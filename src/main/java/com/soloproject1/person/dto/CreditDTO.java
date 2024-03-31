package com.soloproject1.person.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class CreditDTO extends PersonDTO {
	
	@JsonProperty("original_name")
	private String originalName;
	
	@JsonProperty("cast_id")
	private int castId;
	
	private String character;
	
	@JsonProperty("credit_id")
	private String creditId;
	
	private int order;
	
}
