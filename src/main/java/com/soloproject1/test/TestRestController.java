package com.soloproject1.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soloproject1.comment.mapper.CommentMapper;
import com.soloproject1.content.entity.ContentEntity;
import com.soloproject1.content.repository.ContentRepository;

@RequestMapping("/test")
@RestController
public class TestRestController {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ContentRepository contentRepository;
	
	@RequestMapping("/1")
	public String test1() {
		return "hello world";
	}
	
	@RequestMapping("/2")
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		result.put("testKey", "testValue");
		return result;
	}
	
	@RequestMapping("/3")
	public Map<String, Object> test3() {
		return commentMapper.test3();
	}
	
	@RequestMapping("/4")
	public List<ContentEntity> test4() {
		return contentRepository.findAll();
	}
	
}
