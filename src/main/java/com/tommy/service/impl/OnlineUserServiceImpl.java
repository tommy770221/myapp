package com.tommy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tommy.dao.OnlineUserDAO;
import com.tommy.model.hibernate.OnlineUser;
import com.tommy.service.OnlineUserService;

public class OnlineUserServiceImpl implements OnlineUserService {

	@Autowired
	private OnlineUserDAO onlineUserDAO;
	
	public List<OnlineUser> findAll() {

		return this.onlineUserDAO.findAll();
	}

	public void save(OnlineUser onlineUser) {
		this.onlineUserDAO.save(onlineUser);

	}

	public void remove(String sessionId) {
		this.onlineUserDAO.remove(sessionId);
	}

	public void removeAll() {
		this.onlineUserDAO.removeAll();
	}

	public OnlineUser findByOnlineUserId(String onlineUserId) {
		return this.onlineUserDAO.findByOnlineUserId(onlineUserId);
	}

	public List<OnlineUser> findOnlineUsersById(String onlineUserId) {
	    return this.onlineUserDAO.findOnlineUsersById(onlineUserId);
	}

	public OnlineUser findByOnlineSessionId(String sessionId) {
		return this.onlineUserDAO.findByOnlineSessionId(sessionId);
	}

	public List<OnlineUser> findOnlineUsersBySessionId(String sessionId) {
		return this.onlineUserDAO.findOnlineUsersBySessionId(sessionId);
	}

}
