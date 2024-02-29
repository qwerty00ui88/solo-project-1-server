package com.soloproject1.statistics.bo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.bo.ContentBO;
import com.soloproject1.statistics.domain.StatisticsView;

@Service
public class StatisticsBO {

	@Autowired
	private ContentBO contentBO;
	
	public StatisticsView generateStatisticsViewByRegionGenderAgeGroup(
			String region, 
			String gender, 
			Integer ageGroup) {
		
		Integer startBirthYear = null;
		Integer endBirthYear = null;
		if (ageGroup != null) {
			int currentYear = LocalDate.now().getYear();
			startBirthYear = currentYear - (ageGroup + 9);
			endBirthYear = currentYear - ageGroup;
		}
		
		StatisticsView statisticsView = new StatisticsView();
		statisticsView.setBestContent(contentBO.getBestContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
		statisticsView.setWorstContent(contentBO.getWorstContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
		statisticsView.setMostSelectedFavoriteContent(contentBO.getMostSelectedFavoriteContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
		
		return statisticsView;
	}
	
}
