package com.dt.hrbu.service;

import java.util.List;

import com.dt.hrbu.vo.User;

public interface LoginService {
	public User login(User user);

	public  List<User> getUserList() ;
}
