package com.soloproject1.mainpage.domain;

import java.util.List;

import com.soloproject1.content.dto.ContentDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MainPageView {

	private List<ContentDTO> allTrending;
	private List<ContentDTO> movieTreding;
	private List<String> allTrendingVideo;
	
}
