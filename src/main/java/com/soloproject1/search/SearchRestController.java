package com.soloproject1.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.search.bo.SearchBO;
import com.soloproject1.search.dto.SearchResultsDTO;

@RestController
public class SearchRestController {

	@Autowired
	private SearchBO searchBO;
	
	@GetMapping("/search")
	public SearchResultsDTO getSearchResultsByQuery(@RequestParam("query") String query, @RequestParam("page") int page) {
		return searchBO.getSearchResultsByQuery(query, page);
	}
	
}
