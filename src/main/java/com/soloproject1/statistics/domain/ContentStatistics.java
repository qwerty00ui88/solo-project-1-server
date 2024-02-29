package com.soloproject1.statistics.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentStatistics {	
	private String mediaType;
	private int tmdbId;
	private int count;
}
