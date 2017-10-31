package com.zyj.dao;

import com.zyj.entity.User;

public interface UserDao {
	public User Login(User user);
	public boolean Register(User user);
	public void Update(User user);
	User getUserByCondition(String condition);
}
