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
import com.soloproject1.tmdb.content.ContentDTO;
import com.soloproject1.tmdb.content.ContentDetailDTO;

import reactor.core.publisher.Mono;

@Service
public class TmdbBO {
	private WebClient tmdbWebClient;

	public TmdbBO(@Qualifier("tmdbWebClient") WebClient tmdbWebClient) {
		this.tmdbWebClient = tmdbWebClient;
	}
	
	public ContentDetailDTO getContentDetail(String mediaType, int tmdbId) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");
		queryParams.add("append_to_response", "credits");

		Map<String, Object> result = tmdbWebClient.get()
				.uri(uriBuilder -> uriBuilder.path("/" + mediaType + "/" + tmdbId).queryParams(queryParams).build())
				.retrieve().bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
				}).block();

		ContentDetailDTO contentDetailDTO = new ContentDetailDTO(result, mediaType);
		return contentDetailDTO;
	}
	
	public List<ContentDTO> getTrendingList(String category, String duration) {
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("language", "ko-KR");

		return tmdbWebClient
				.get().uri(uriBuilder -> uriBuilder.path("/trending/" + category + "/" + duration)
						.queryParams(queryParams).build())
				.retrieve().bodyToMono(TmdbResponseDTO.class).flatMap(responseBody -> {
					List<Map<String, Object>> results = responseBody.getResults();
					List<ContentDTO> allTrending = results.stream().map(result -> new ContentDTO(result))
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
		return videoKey.subList(0, 3);
	}
	
}
