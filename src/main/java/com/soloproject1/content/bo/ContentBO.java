package com.soloproject1.content.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.soloproject1.content.dto.ContentDetailDTO;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.repository.ContentRepository;

@Service
public class ContentBO {

	@Autowired
	private ContentRepository contentRepository;

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

	public ContentDetailDTO getContentDetail(String mediaType, int tmdbId) {
		ContentDetailDTO contentDetail = webClient.get().uri(mediaType + "/" + String.valueOf(tmdbId)).retrieve()
				.bodyToMono(ContentDetailDTO.class).block();
		return contentDetail;
	}

}
