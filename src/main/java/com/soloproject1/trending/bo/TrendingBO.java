package com.soloproject1.trending.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.dto.TmdbContentDTO;

@Service
public class TrendingBO {

	@Autowired
	private TmdbBO tmdbBO;
	
	public List<TmdbContentDTO> getTrendingItemList(@PathVariable("category") String category, @PathVariable("period") String period) {
		return tmdbBO.getTrendingItemList(category, period);
	}
	
}
