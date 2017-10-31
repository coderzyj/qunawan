package com.zyj.serviceImp;

import javax.annotation.Resource;
import javax.transaction.Transactional;






import org.springframework.stereotype.Service;

import com.zyj.dao.UserDao;
import com.zyj.entity.User;
import com.zyj.service.UserService;

@Service
public class UserServiceImp implements UserService{

private UserDao userDao;
	
	@Resource(name="userDaoImp")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public User Login(User user) {
		// TODO Auto-generated method stub
		return userDao.Login(user);
	}

	@Transactional
	public boolean Register(User user) {
		// TODO Auto-generated method stub
		return userDao.Register(user);
	}

	@Transactional
	public void Update(User user) {
		// TODO Auto-generated method stub
		userDao.Update(user);
	}

	@Override
	public boolean checkPhoneAndEmail(String phone, String email, User user) {
		// TODO Auto-generated method stub
		if (!phone.equals(user.getPhone()))
			if (userDao.getUserByCondition(phone)!=null)
				return false;

		if (!email.equals(user.getEmail()))
			if (userDao.getUserByCondition(email)!=null)
				return false;

		return true;
	}
}
