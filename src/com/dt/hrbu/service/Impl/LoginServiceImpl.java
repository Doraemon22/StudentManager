package com.dt.hrbu.service.Impl;

import java.util.List;

import com.dt.hrbu.dao.UserDao;
import com.dt.hrbu.service.LoginService;
import com.dt.hrbu.vo.User;

public class LoginServiceImpl implements LoginService {
	private UserDao userDao=new UserDao();
	@Override
	public User login(User user) {
	
		User dbUser=userDao.getUsersByUsername(user.getUsername());
		//对比密码，判断是否成功登录
		if(dbUser!=null&&dbUser.getPassword()!=null&&dbUser.getPassword().equals(user.getPassword())) {
			return user;
		}else {
			return null;
		}
	}
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
}
