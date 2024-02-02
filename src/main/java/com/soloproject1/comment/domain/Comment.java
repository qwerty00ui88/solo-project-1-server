package com.soloproject1.comment.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {
	private int id;
	private int contentId;
	private int userId;
	private String text;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
