//package com.soloproject1.statisticsModal.bo;
//
//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.soloproject1.content.bo.ContentBO;
//import com.soloproject1.statisticsModal.dto.StatisticsModalView;
//
//
//public class StatisticsModalBO {
//	@Autowired
//	private ContentBO contentBO;
//	
//	public StatisticsModalView generateStatisticsViewByRegionGenderAgeGroup(
//			String region, 
//			String gender, 
//			Integer ageGroup) {
//		
//		Integer startBirthYear = null;
//		Integer endBirthYear = null;
//		if (ageGroup != null) {
//			int currentYear = LocalDate.now().getYear();
//			startBirthYear = currentYear - (ageGroup + 9);
//			endBirthYear = currentYear - ageGroup;
//		}
//		
//		StatisticsModalView statisticsModalView = new StatisticsModalView();
//		statisticsModalView.setBestContent(contentBO.getBestContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
//		statisticsModalView.setWorstContent(contentBO.getWorstContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
//		statisticsModalView.setMostSelectedFavoriteContent(contentBO.getMostSelectedFavoriteContentByRegionGenderBirth(region, gender, startBirthYear, endBirthYear));
//		
//		return statisticsModalView;
//	}
//}
