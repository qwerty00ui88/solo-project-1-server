package com.soloproject1.content.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.soloproject1.content.dto.ContentDTO;
import com.soloproject1.content.dto.MovieDTO;
import com.soloproject1.content.dto.TVDTO;
import com.soloproject1.content.dto.TmdbResponseDTO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.mapper.ContentMapper;
import com.soloproject1.content.repository.ContentRepository;
import com.soloproject1.statistics.domain.ContentDetailStatistics;
import com.soloproject1.statistics.domain.ContentStatistics;

import reactor.core.publisher.Mono;

@Service
public class ContentBO {

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private ContentMapper contentMapper;

	private WebClient tmdbWebClient;

	public ContentBO(@Qualifier("tmdbWebClient") WebClient tmdbWebClient) {
		this.tmdbWebClient = tmdbWebClient;
	}

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

		ResponseSpec responseSpec = tmdbWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/" + mediaType + "/" + tmdbId).queryParams(queryParams).build())
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

		if (contentStatistics != null) {
			contentDetailStatistics
					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
			contentDetailStatistics.setCount(contentStatistics.getCount());
		}

		return contentDetailStatistics;
	}

	public ContentDetailStatistics getWorstContentByRegionGenderBirth(String region, String gender,
			Integer startBirthYear, Integer endBirthYear) {

		ContentStatistics contentStatistics = contentMapper.selectWorstContentByRegionGenderBirth(region, gender,
				startBirthYear, endBirthYear);
		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
		if (contentStatistics != null) {
			contentDetailStatistics
					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
			contentDetailStatistics.setCount(contentStatistics.getCount());
		}
		return contentDetailStatistics;
	}

	public ContentDetailStatistics getMostSelectedFavoriteContentByRegionGenderBirth(String region, String gender,
			Integer startBirthYear, Integer endBirthYear) {

		ContentStatistics contentStatistics = contentMapper.selectMostSelectedFavoriteContentByRegionGenderBirth(region,
				gender, startBirthYear, endBirthYear);
		ContentDetailStatistics contentDetailStatistics = new ContentDetailStatistics();
		if (contentStatistics != null) {
			contentDetailStatistics
					.setContent(getContentDetail(contentStatistics.getMediaType(), contentStatistics.getTmdbId()));
			contentDetailStatistics.setCount(contentStatistics.getCount());
		}
		return contentDetailStatistics;
	}

	public List<ContentDTO> getTrendingList(String category, String duration) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		return tmdbWebClient
				.get().uri(uriBuilder -> uriBuilder.path("/trending/" + category + "/" + duration)
						.queryParams(queryParams).build())
				.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
					List<Map<String, Object>> results = responseBody.getResults();
					List<ContentDTO> allTrending = results.stream().map(ContentDTO::createDTO)
							.collect(Collectors.toList());
					return Mono.just(allTrending);
				}).block();
	}

	public List<String> getAllTrendingVideoList(List<ContentDTO> allTrendingList) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		Random random = new Random();
		List<String> videoKey = new ArrayList<>();
		for (ContentDTO content : allTrendingList) {
			tmdbWebClient.get()
					.uri(uriBuilder -> uriBuilder
							.path("/" + content.getMediaType() + "/" + content.getTmdbId() + "/videos")
							.queryParams(queryParams).build())
					.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
						// 한 컨텐츠에 대한 비디오 리스트
						List<Map<String, Object>> videoList = responseBody.getResults();
						// 하나만 추출하여 리스트에 저장
						if (videoList.size() > 0) {
							int randNum = random.nextInt(videoList.size());
							videoKey.add((String) videoList.get(randNum).get("key"));
						}
						return Mono.empty();
					}).block();
		}
		return videoKey.subList(0, 7);
	}

}
