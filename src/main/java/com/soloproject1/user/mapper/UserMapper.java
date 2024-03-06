package com.soloproject1.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.soloproject1.user.domain.User;

@Mapper
public interface UserMapper {

	public Integer insertUser(User user);
	
	public User selectUserByNicknamePassword(
			@Param("nickname") String nickname, 
			@Param("password") String password);
	
	public User selectUserByUserId(int id);
	
	public void updateEmailVerifiedByUserId(
			@Param("userId") int userId, 
			@Param("emailVerified") boolean emailVerified);
	
}
