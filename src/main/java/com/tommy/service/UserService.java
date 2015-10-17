package com.tommy.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tommy.model.User;

@Service
public interface UserService {

	public List<User> findAll();

	public void save(User user);

	public void remove(String userId);

	public User findByUserId(String userId);

	public void update(User user);

	public User findUserByEmail(String email);
}
