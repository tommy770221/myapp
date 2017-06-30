package com.tommy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tommy.dao.UserDAO;
import com.tommy.model.hibernate.User;

@Repository
@Transactional(value = "transactionManagerTwo")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext(unitName = "persistenceUnitTwo")
	@Qualifier(value = "entityManagerFactoryTwo")
	private EntityManager manager;


	public List<User> findAllUsers() {
		String hql = "from User order by id desc";
		return (List<User>)  manager
				.createQuery(hql).getResultList();
	}


	public User findUserByUserId(String userId) {
		User user = (User)  manager.find(
				User.class, userId);
		return user;
	}


	public void removeUser(String userId) {
		 manager.remove(findUserByUserId(userId));
	}


	@Transactional(value = "transactionManagerTwo")
	public void saveUser(User user) {
		 manager.persist(user);
	}


	public void updateUser(User user) {
		 manager.merge(user);
	}


	public User findUserByUsername(final String userName) {
		final String hql = "select u from User u where u.username=:userName";
		User user = (User)  manager.createQuery(hql).setParameter("userName", userName).getSingleResult();
		return user;
	}


	public User findUserByEmail(final String email) {
		final String hql = "select u from User u where u.email=:email";
		User user = (User) manager.createQuery(hql).setParameter("email", email).getSingleResult();
		return user;
	}

}
