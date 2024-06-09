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

@Service
public class TmdbBO {
    private WebClient tmdbWebClient;

    public TmdbBO(@Qualifier("tmdbWebClient") WebClient tmdbWebClient) {
        this.tmdbWebClient = tmdbWebClient;
    }
    
    private <T> T getTmdbResponse(String path, Class<T> responseType) {
    	MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("language", "ko-KR");
        
    	return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    private <T> T getTmdbResponse(String path, MultiValueMap<String, String> queryParams, Class<T> responseType) {
    	queryParams.add("language", "ko-KR");
    	return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }
    
    private <T> T getTmdbResponse(String path, MultiValueMap<String, String> queryParams, ParameterizedTypeReference<T> responseType) {
    	queryParams.add("language", "ko-KR");
    	return tmdbWebClient.get()
                .uri(uriBuilder -> uriBuilder.path(path)
                        .queryParams(queryParams)
                        .build())
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public TmdbContentDetailDTO getContentDetail(String mediaType, int tmdbId) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("append_to_response", "credits");
        
        Map<String, Object> result = getTmdbResponse("/" + mediaType + "/" + tmdbId, queryParams, new ParameterizedTypeReference<>() {});
        return new TmdbContentDetailDTO(result, mediaType);
    }

    public List<TmdbContentDTO> getTrendingItemList(String category, String period) {
        TmdbResponseDTO responseBody = getTmdbResponse("/trending/" + category + "/" + period, TmdbResponseDTO.class);
        List<Map<String, Object>> results = responseBody.getResults();
        return results.stream()
                .map(result -> new TmdbContentDTO(result, category))
                .collect(Collectors.toList());
    }

    public List<TmdbContentDTO> getSearchMovieListByQuery(String query, int page) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("query", query);
        queryParams.add("page", String.valueOf(page));

        TmdbResponseDTO responseBody = getTmdbResponse("/search/movie", queryParams, TmdbResponseDTO.class);
        List<Map<String, Object>> results = responseBody.getResults();
        return results.stream()
                .map(result -> new TmdbContentDTO(result, "movie"))
                .collect(Collectors.toList());
    }

    public List<TmdbContentDTO> getSearchTvListByQuery(String query, int page) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("query", query);
        queryParams.add("page", String.valueOf(page));

        TmdbResponseDTO responseBody = getTmdbResponse("/search/tv", queryParams, TmdbResponseDTO.class);
        List<Map<String, Object>> results = responseBody.getResults();
        return results.stream()
                .map(result -> new TmdbContentDTO(result, "tv"))
                .collect(Collectors.toList());
    }

    public List<String> getAllTrendingVideoList(List<TmdbContentDTO> allTrendingList) {
        Random random = new Random();
        List<String> videoKey = new ArrayList<>();
        for (TmdbContentDTO content : allTrendingList) {
            TmdbResponseDTO responseBody = getTmdbResponse("/" + content.getMediaType() + "/" + content.getTmdbId() + "/videos", TmdbResponseDTO.class);
            List<Map<String, Object>> videoList = responseBody.getResults();
            if (videoList.size() > 0) {
                int randNum = random.nextInt(videoList.size());
                videoKey.add((String) videoList.get(randNum).get("key"));
            }
        }
        return videoKey.subList(0, 3);
    }
}
