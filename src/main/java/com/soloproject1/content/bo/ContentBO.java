package com.soloproject1.content.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.repository.ContentRepository;

@Service
public class ContentBO {

	@Autowired
	private ContentRepository contentRepository;

	public int addContent(String mediaType, int tmdbId) {
		ContentEntity content = contentRepository.save(ContentEntity.builder().mediaType(mediaType).tmdbId(tmdbId).build());
		return content.getId();
	}

	public ContentEntity getContentByMediaTypeAndTmdbId(String mediaType, int tmdbId) {
		return contentRepository.findContentByMediaTypeAndTmdbId(mediaType, tmdbId);
	}

}
