package com.soloproject1.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soloproject1.user.domain.User;
import com.soloproject1.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	public Integer addUser(User user) {
		return userMapper.insertUser(user);
	}
	
	public User getUserByNicknamePassword(String nickname, String password) {
		return userMapper.selectUserByNicknamePassword(nickname, password);
	}
	
	public User getUserByUserId(int id) {
		return userMapper.selectUserByUserId(id);
	}
	
}
