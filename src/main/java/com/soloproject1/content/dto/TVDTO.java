package com.soloproject1.content.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class TVDTO extends ContentDTO {

	private String name;

	@JsonProperty("original_name")
	private String originalName;

	@JsonProperty("first_air_date")
	private Date firstAirDate;

}
