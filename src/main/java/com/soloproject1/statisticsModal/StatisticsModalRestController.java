//package com.soloproject1.statisticsModal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.soloproject1.statisticsModal.bo.StatisticsModalBO;
//import com.soloproject1.statisticsModal.dto.StatisticsModalView;
//
//@RequestMapping("/statistics")
//@RestController
//public class StatisticsModalRestController {
//
//	@Autowired
//	private StatisticsModalBO statisticsModalBO;
//	
//	@GetMapping("/by-region-gender-ageGroup")
//	public StatisticsModalView getStatisticsByRegionGenderAgeGroup(
//			@RequestParam(value="region", required=false) String region,
//			@RequestParam(value="gender", required=false) String gender,
//			@RequestParam(value="ageGroup", required=false) Integer ageGroup) {
//		
//		return statisticsModalBO.generateStatisticsViewByRegionGenderAgeGroup(region, gender, ageGroup);
//	}
//	
//}
