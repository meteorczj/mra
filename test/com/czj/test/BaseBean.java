package com.czj.test;

import java.util.ArrayList;
import java.util.List;

import com.czj.entity.User;

public class BaseBean {
	private List<User> userList;

	public BaseBean() {
		userList = new ArrayList<User>();
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public void addUser(User user) {
		userList.add(user);
	}
}
