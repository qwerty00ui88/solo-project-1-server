package com.soloproject1.statistics.domain;

import com.soloproject1.content.dto.ContentDTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ContentDetailStatistics {
	private ContentDTO content;
	private int count;
}
