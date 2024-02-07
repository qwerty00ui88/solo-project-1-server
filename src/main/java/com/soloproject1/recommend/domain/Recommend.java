package com.soloproject1.recommend.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Recommend {
	private int contentId;
	private int userId;
	private String status;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
