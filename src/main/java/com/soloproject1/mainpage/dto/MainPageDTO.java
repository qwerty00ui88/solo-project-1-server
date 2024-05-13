package com.soloproject1.mainpage.dto;

import java.util.List;

import com.soloproject1.tmdb.content.ContentDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MainPageDTO {

	private List<ContentDTO> allTrending;
	private List<ContentDTO> movieTrending;
	private List<String> allTrendingVideo;
	
}
