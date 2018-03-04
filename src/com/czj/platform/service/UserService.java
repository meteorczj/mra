package com.czj.platform.service;

import java.util.List;

import com.czj.platform.entity.User;

public interface UserService {
	
	public int selectUserCount(User entity);
	
	public List<User> selectUserList(User entity);
	
	public User selectUser(User entity);
	
	public int insertUser(User entity);
	
	public int updateUser(User entity);
	
	public int deleteUser(User entity);
	
}
