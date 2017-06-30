package com.tommy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tommy.dao.RoleDAO;
import com.tommy.model.hibernate.Role;

@Repository
@Transactional(value = "transactionManagerTwo")
public class RoleDAOImpl implements RoleDAO{

	 @PersistenceContext(unitName = "persistenceUnitTwo")
	 @Qualifier(value = "entityManagerFactoryTwo")
	 private EntityManager manager;


	public void saveRole(Role role) {
		manager.persist(role);
		
	}


	public void removeRole(Long id) {
		manager.remove(findRoleById(id));
		
	}


	public Role findRoleById(final Long id) {
		final String hql = "select u from Role u where u.id=:id";
		Role role=(Role) manager.createQuery(hql).setParameter("id", id).getSingleResult();
		return role;
	}


	public Role findRoleByRolename(final String roleName) {
		final String hql = "select u from Role u where u.name=:roleName";
		Role role=(Role) manager.createQuery(hql).setParameter("roleName", roleName).getSingleResult();
           
		return role;
	}


	public List<Role> findAllRoles() {
		final String hql = "select u from Role ";
		 List<Role> roles=manager.createQuery(hql).getResultList();
		return roles;
	}

	public void updateRole(Role role) {
		manager.merge(role);
	}

}
