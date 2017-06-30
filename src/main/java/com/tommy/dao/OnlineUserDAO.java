package com.tommy.dao;

import java.util.List;

import com.tommy.model.hibernate.OnlineUser;

public interface OnlineUserDAO {

	public List<OnlineUser> findAll();

	public void save(OnlineUser onlineUser);

	public void remove(String sessionId);
	
	public void removeAll();

	public OnlineUser findByOnlineUserId(String onlineUserId);
	
	public List<OnlineUser> findOnlineUsersById(String onlineUserId);
	
    public OnlineUser findByOnlineSessionId(String sessionId);
	
	public List<OnlineUser> findOnlineUsersBySessionId(String sessionId);
	
	
}
