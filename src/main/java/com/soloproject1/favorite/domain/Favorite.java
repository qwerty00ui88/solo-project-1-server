package com.soloproject1.favorite.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Favorite {
	private int contentId;
	private int userId;
	private ZonedDateTime createdAt;
}
