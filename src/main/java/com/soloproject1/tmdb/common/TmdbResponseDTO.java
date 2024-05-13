package com.soloproject1.tmdb.common;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TmdbResponseDTO {
private int page;
	
	private List<Map<String, Object>> results;
	
	@JsonProperty("total_pages")
	private int totalPages;
	
	@JsonProperty("total_results")
	private int totalResults;
}
