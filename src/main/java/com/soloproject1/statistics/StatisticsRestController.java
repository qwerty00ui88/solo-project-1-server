package com.soloproject1.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.statistics.bo.StatisticsBO;
import com.soloproject1.statistics.domain.StatisticsView;

@RequestMapping("/statistics")
@RestController
public class StatisticsRestController {

	@Autowired
	private StatisticsBO statisticsBO;
	
	@GetMapping("/by-region-gender-ageGroup")
	public StatisticsView getStatisticsByRegionGenderAgeGroup(
			@RequestParam("region") String region,
			@RequestParam("gender") String gender,
			@RequestParam("ageGroup") Integer ageGroup) {
		
		return statisticsBO.generateStatisticsViewByRegionGenderAgeGroup(region, gender, ageGroup);
	}
	
}
