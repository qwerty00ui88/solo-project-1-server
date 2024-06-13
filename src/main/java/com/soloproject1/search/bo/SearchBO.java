package com.soloproject1.search.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.search.dto.SearchResultsDTO;
import com.soloproject1.tmdb.bo.TmdbBO;

@Service
public class SearchBO {
	
	@Autowired
	private TmdbBO tmdbBO;
	
	public SearchResultsDTO getSearchResultsByQuery(String query, int page) {
		
		SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
		searchResultsDTO.setMovieList(tmdbBO.getSearchMovieListByQuery(query, page));
		searchResultsDTO.setTvList(tmdbBO.getSearchTvListByQuery(query, page));
		
		return searchResultsDTO;
	}
	
}
