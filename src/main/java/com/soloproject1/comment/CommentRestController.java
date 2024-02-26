package com.soloproject1.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.comment.bo.CommentBO;
import com.soloproject1.comment.entity.CommentEntity;
import com.soloproject1.content.bo.ContentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;

	@Autowired
	private ContentBO contentBO;

	@PostMapping("/create")
	public Map<String, Object> createComment(
			@RequestBody Map<String, Object> request,
			HttpSession session) {
		String mediaType = (String) request.get("mediaType");
		int tmdbId = (int) request.get("tmdbId");
		String text = (String) request.get("text");

		int userId = (int)session.getAttribute("userId");

		// DB INSERT
		Map<String, Object> result = commentBO.addComment(userId, mediaType, tmdbId, text);

		return result;
	}

	@PutMapping("/update")
	public Map<String, Object> updateComment(@RequestBody Map<String, Object> request) {
		int commentId = (int) request.get("commentId");
		String text = (String) request.get("text");

		// DB UPDATE
		CommentEntity comment = commentBO.updateCommentById(commentId, text);

		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (comment != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "댓글 수정에 실패하였습니다. 관리자에게 문의해주세요.");
		}
		return result;
	}

	@DeleteMapping("/delete")
	public Map<String, Object> deleteComment(@RequestBody Map<String, Object> request) {
		int commentId = (int) request.get("commentId");

		// DB DELETE
		commentBO.deleteCommentById(commentId);

		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}

}
