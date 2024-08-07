package com.soloproject1.content.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.repository.ContentRepository;
import com.soloproject1.tmdb.bo.TmdbBO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

@Service
public class ContentBO {

	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private TmdbBO tmdbBO;
	
	public int addContent(String mediaType, int tmdbId, String title, String originalTitle, String posterPath,
			String backdropPath) {
		ContentEntity content = contentRepository.save(ContentEntity.builder().mediaType(mediaType).tmdbId(tmdbId)
				.title(title).originalTitle(originalTitle).posterPath(posterPath).backdropPath(backdropPath).build());
		return content.getId();
	}

	public ContentEntity getContentByMediaTypeAndTmdbId(String mediaType, int tmdbId) {
		return contentRepository.findContentByMediaTypeAndTmdbId(mediaType, tmdbId);
	}

	public ContentEntity getContentById(int id) {
		return contentRepository.findById(id).orElse(null);
	}
	
	public TmdbContentDetailDTO getContentDetailById(String mediaType, int tmdbId) {
		return tmdbBO.getContentDetail(mediaType, tmdbId);
	}


//	public ContentDetailStatistics getBestContentByRegionGenderBirth(String region, String gender,
//			Integer startBirthYear, Integer endBirthYear) {
//
//		ContentStatistics contentStatistics = contentMapper.selectBestContentByRegionGenderBirth(region, gender,
//				startBirthYear, endBirthYear);
//		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
//
//		if (contentStatistics != null) {
//			contentDetailStatistics
//					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
//			contentDetailStatistics.setCount(contentStatistics.getCount());
//		}
//
//		return contentDetailStatistics;
//	}

//	public ContentDetailStatistics getWorstContentByRegionGenderBirth(String region, String gender,
//			Integer startBirthYear, Integer endBirthYear) {
//
//		ContentStatistics contentStatistics = contentMapper.selectWorstContentByRegionGenderBirth(region, gender,
//				startBirthYear, endBirthYear);
//		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
//		if (contentStatistics != null) {
//			contentDetailStatistics
//					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
//			contentDetailStatistics.setCount(contentStatistics.getCount());
//		}
//		return contentDetailStatistics;
//	}
//
//	public ContentDetailStatistics getMostSelectedFavoriteContentByRegionGenderBirth(String region, String gender,
//			Integer startBirthYear, Integer endBirthYear) {
//
//		ContentStatistics contentStatistics = contentMapper.selectMostSelectedFavoriteContentByRegionGenderBirth(region,
//				gender, startBirthYear, endBirthYear);
//		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
//		if (contentStatistics != null) {
//			contentDetailStatistics
//					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
//			contentDetailStatistics.setCount(contentStatistics.getCount());
//		}
//		return contentDetailStatistics;
//	}

}
