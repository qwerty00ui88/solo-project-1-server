package com.soloproject1.tmdb.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.soloproject1.tmdb.common.TmdbResponseDTO;
import com.soloproject1.tmdb.dto.TmdbContentDTO;
import com.soloproject1.tmdb.dto.TmdbContentDetailDTO;

import reactor.core.publisher.Mono;

@Service
public class TmdbBO {
	private WebClient tmdbWebClient;

	public TmdbBO(@Qualifier("tmdbWebClient") WebClient tmdbWebClient) {
		this.tmdbWebClient = tmdbWebClient;
	}
	
	public TmdbContentDetailDTO getContentDetail(String mediaType, int tmdbId) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");
		queryParams.add("append_to_response", "credits");

		Map<String, Object> result = tmdbWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/" + mediaType + "/" + tmdbId).queryParams(queryParams).build())
				.retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
				}).block();

		TmdbContentDetailDTO contentDetailDTO = new TmdbContentDetailDTO(result, mediaType);
		return contentDetailDTO;
	}
	
	public List<TmdbContentDTO> getTrendingItemList(String category, String period) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		return tmdbWebClient
				.get().uri(uriBuilder -> uriBuilder.path("/trending/" + category + "/" + period)
						.queryParams(queryParams).build())
				.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
					List<Map<String, Object>> results = responseBody.getResults();
					List<TmdbContentDTO> tmdbContentDTOList = results.stream().map(result -> new TmdbContentDTO(result, category))
							.collect(Collectors.toList());
					return Mono.just(tmdbContentDTOList);
				}).block();
	}
	
	public List<TmdbContentDTO> getSearchMovieListByQuery(String query, int page) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");
		queryParams.add("query", query);
		queryParams.add("page", String.valueOf(page));

		return tmdbWebClient
				.get().uri(uriBuilder -> uriBuilder.path("/search/movie")
						.queryParams(queryParams).build())
				.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
					List<Map<String, Object>> results = responseBody.getResults();
					List<TmdbContentDTO> tmdbContentList = results.stream().map(result -> new TmdbContentDTO(result, "movie"))
							.collect(Collectors.toList());
					return Mono.just(tmdbContentList);
				}).block();
	}
	
	public List<TmdbContentDTO> getSearchTvListByQuery(String query, int page) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");
		queryParams.add("query", query);
		queryParams.add("page", String.valueOf(page));

		return tmdbWebClient
				.get().uri(uriBuilder -> uriBuilder.path("/search/movie")
						.queryParams(queryParams).build())
				.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
					List<Map<String, Object>> results = responseBody.getResults();
					List<TmdbContentDTO> tmdbContentList = results.stream().map(result -> new TmdbContentDTO(result, "movie"))
							.collect(Collectors.toList());
					return Mono.just(tmdbContentList);
				}).block();
	}

	public List<String> getAllTrendingVideoList(List<TmdbContentDTO> allTrendingList) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		Random random = new Random();
		List<String> videoKey = new ArrayList<>();
		for (TmdbContentDTO content : allTrendingList) {
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
		return videoKey.subList(0, 3);
	}
	
}
