package com.soloproject1.trending;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.tmdb.dto.TmdbContentDTO;
import com.soloproject1.trending.bo.TrendingBO;

@RequestMapping("/trending")
@RestController
public class TrendingRestController {
	
	@Autowired
	private TrendingBO trendingBO;
	
	@GetMapping("/{category}/{period}")
	public List<TmdbContentDTO> getTrendingItemList(@PathVariable("category") String category, @PathVariable("period") String period) {
		return trendingBO.getTrendingItemList(category, period);
	}
	
}
