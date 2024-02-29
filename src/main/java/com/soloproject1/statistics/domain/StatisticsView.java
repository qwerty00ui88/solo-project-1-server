package com.soloproject1.statistics.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StatisticsView {

	private ContentDetailStatistics bestContent;
	private ContentDetailStatistics worstContent;
	private ContentDetailStatistics mostSelectedFavoriteContent;
	
}
