package com.czj.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czj.platform.dao.UserDao;
import com.czj.platform.entity.User;
import com.czj.platform.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User selectUser(User entity) {
		return this.userDao.selectUser(entity);
	}

	@Override
	public int insertUser(User entity) {
		return  userDao.insertUser(entity);
	}

	@Override
	public int deleteUser(User entity) {
		return userDao.deleteUser(entity);
	}
	
	@Override
	public int selectUserCount(User entity) {
		return userDao.selectUserCount(entity);
	}

	@Override
	public List<User> selectUserList(User entity) {
		return userDao.selectUserList(entity);
	}

	@Override
	public int updateUser(User entity) {
		return userDao.updateUser(entity);
	}

}
