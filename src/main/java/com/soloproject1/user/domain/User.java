package com.soloproject1.user.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {
	private int id;
	private String name;
	private String nickname;
	private String email;
	private Boolean emailVerified;
	private String password;
	private int birth;
	private String gender;
	private String region;
	private String profileImagePath;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
