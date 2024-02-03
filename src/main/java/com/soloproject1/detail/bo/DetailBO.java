package com.soloproject1.detail.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.domain.CommentView;
import com.soloproject1.detail.domain.DetailView;

@Service
public class DetailBO {

	@Autowired
	private CommentBO commentBO;
	
	public DetailView generateDetailViewList(String mediaType, int tmdbId) {
		
		// @@@ mediaType, tmdbId로 contentId 찾기
		int contentId = 1;
		
		DetailView detailView = new DetailView();
		
		// 코멘트
		List<CommentView> commentViewList = commentBO.generateCommentViewListByContentId(contentId);
		// @@@ 추후 good/bad/unrated로 나누어 할당할 예정
		detailView.setGoodCommentViewList(commentViewList);
		detailView.setBadCommentViewList(commentViewList);
		detailView.setUnratedCommentViewList(commentViewList);
		
		return detailView;
	};
	
}
