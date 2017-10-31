package com.zyj.service;

import com.zyj.entity.User;

public interface UserService {
	public User Login(User user);
	public boolean Register(User user);
	public void Update(User user);
	public boolean checkPhoneAndEmail(String phone, String email, User user);
}
