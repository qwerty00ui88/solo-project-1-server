package com.soloproject1.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.soloproject1.user.domain.User;

@Mapper
public interface UserMapper {

	public Integer insertUser(User user);
	
}
