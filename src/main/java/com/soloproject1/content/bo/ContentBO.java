package com.soloproject1.content.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.soloproject1.content.dto.ContentDTO;
import com.soloproject1.content.dto.MovieDTO;
import com.soloproject1.content.dto.TVDTO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.mapper.ContentMapper;
import com.soloproject1.content.repository.ContentRepository;
import com.soloproject1.statistics.domain.ContentDetailStatistics;
import com.soloproject1.statistics.domain.ContentStatistics;

@Service
public class ContentBO {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private ContentMapper contentMapper;

	@Autowired
	private WebClient webClient;

	public int addContent(String mediaType, int tmdbId) {
		ContentEntity content = contentRepository
				.save(ContentEntity.builder().mediaType(mediaType).tmdbId(tmdbId).build());
		return content.getId();
	}

	public ContentEntity getContentByMediaTypeAndTmdbId(String mediaType, int tmdbId) {
		return contentRepository.findContentByMediaTypeAndTmdbId(mediaType, tmdbId);
	}

	public ContentEntity getContentById(int id) {
		return contentRepository.findById(id).orElse(null);
	}

	public ContentDTO getContentDetail(String mediaType, int tmdbId) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		ResponseSpec responseSpec = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(mediaType + "/" + tmdbId).queryParams(queryParams).build())
				.retrieve();

		if (mediaType.equals("movie")) {
			ContentDTO contentDetail = responseSpec.bodyToMono(MovieDTO.class).block();
			contentDetail.setMediaType(mediaType);
			return contentDetail;
		} else {
			ContentDTO contentDetail = responseSpec.bodyToMono(TVDTO.class).block();
			contentDetail.setMediaType(mediaType);
			return contentDetail;
		}

	}

	public ContentDetailStatistics getBestContentByRegionGenderBirth(String region, String gender,
			Integer startBirthYear, Integer endBirthYear) {

		ContentStatistics contentStatistics = contentMapper.selectBestContentByRegionGenderBirth(region, gender,
				startBirthYear, endBirthYear);
		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
		contentDetailStatistics
				.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
		contentDetailStatistics.setCount(contentStatistics.getCount());

		return contentDetailStatistics;
	}

	public ContentDetailStatistics getWorstContentByRegionGenderBirth(String region, String gender,
			Integer startBirthYear, Integer endBirthYear) {

		ContentStatistics contentStatistics = contentMapper.selectWorstContentByRegionGenderBirth(region, gender,
				startBirthYear, endBirthYear);
		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
		contentDetailStatistics
				.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
		contentDetailStatistics.setCount(contentStatistics.getCount());

		return contentDetailStatistics;
	}

	public ContentDetailStatistics getMostSelectedFavoriteContentByRegionGenderBirth(String region, String gender,
			Integer startBirthYear, Integer endBirthYear) {

		ContentStatistics contentStatistics = contentMapper.selectMostSelectedFavoriteContentByRegionGenderBirth(region,
				gender, startBirthYear, endBirthYear);
		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
		contentDetailStatistics
				.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
		contentDetailStatistics.setCount(contentStatistics.getCount());

		return contentDetailStatistics;
	}

}
