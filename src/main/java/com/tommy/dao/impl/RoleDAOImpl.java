package com.tommy.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tommy.dao.RoleDAO;
import com.tommy.model.Role;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO{

	 @PersistenceContext
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
