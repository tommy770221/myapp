package com.tommy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tommy.dao.OnlineUserDAO;
import com.tommy.model.OnlineUser;


@Repository
@Transactional
public class OnlineUserDAOImpl implements OnlineUserDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public List<OnlineUser> findAll() {
		String hql = "from OnlineUser order by id desc";
		return (List<OnlineUser>)  manager
				.createQuery(hql).getResultList();
	}

	
	public void save(OnlineUser onlineUser) {
		manager.persist(onlineUser);
	}

	
	public void remove(String sessionId) {
		 manager.remove(findByOnlineSessionId(sessionId));
	}


	public void removeAll() {
		manager.createNativeQuery("truncate table online_users;");
	}

	
	public OnlineUser findByOnlineUserId(String onlineUserId) {
		String hql = "select u from OnlineUser u where u.userId=:userId";
		OnlineUser onlineUser=(OnlineUser)manager.createQuery(hql).setParameter("userId",onlineUserId).getSingleResult();
		return onlineUser;
	}


	public List<OnlineUser> findOnlineUsersById(String onlineUserId) {
		String hql = "select u from OnlineUser u where u.userId=:userId";
		List<OnlineUser> onlineUsers=(List<OnlineUser>)manager.createQuery(hql).setParameter("userId",onlineUserId).getResultList();
		return onlineUsers;
	}


	public OnlineUser findByOnlineSessionId(String sessionId) {
		String hql = "select u from OnlineUser u where u.sessionId=:sessionId";
		OnlineUser onlineUser=(OnlineUser)manager.createQuery(hql).setParameter("sessionId",sessionId).getSingleResult();
		return onlineUser;
	}


	public List<OnlineUser> findOnlineUsersBySessionId(String sessionId) {
		String hql = "select u from OnlineUser u where u.sessionId=:sessionId";
		List<OnlineUser> onlineUsers=(List<OnlineUser>)manager.createQuery(hql).setParameter("sessionId",sessionId).getResultList();
		return onlineUsers;
	}


}
