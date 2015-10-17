package com.tommy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tommy.dao.UserDAO;
import com.tommy.model.User;
import com.tommy.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;


	public void remove(String id) {
		this.userDao.removeUser(id);
	}

	public List<User> findAll() {
		return this.userDao.findAllUsers();
	}

	public User findByUserId(String userId) {

		return this.userDao.findUserByUserId(userId);
	}

	public void save(User user) {
		this.userDao.saveUser(user);
	}

	public void update(User user) {
		this.userDao.updateUser(user);
	}

	public User findUserByEmail(String email) {

		return this.userDao.findUserByEmail(email);
	}

}
