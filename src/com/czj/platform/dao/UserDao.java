package com.czj.platform.dao;

import java.util.List;

import com.czj.platform.entity.User;

public interface UserDao {
	
	int selectUserCount(User entity);
	
	List<User> selectUserList(User entity);
	
	User selectUser(User entity);
	
	int insertUser(User entity);
	
	int updateUser(User entity);
	
	int deleteUser(User entity);
	
}
