package com.soloproject1.search.dto;

import java.util.List;

import com.soloproject1.tmdb.dto.TmdbContentDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class SearchResultsDTO {

	private List<TmdbContentDTO> movieList;
	private List<TmdbContentDTO> tvList;
	
}
