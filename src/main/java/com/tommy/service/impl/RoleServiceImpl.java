package com.tommy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tommy.dao.RoleDAO;
import com.tommy.model.Role;
import com.tommy.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;	
	
	public void removeRole(Long id) {
		 this.roleDAO.removeRole(id);
		
	}

	public Role findRoleById(Long id) {
		Role role=this.roleDAO.findRoleById(id);
		return role;
	}


	public Role findRoleByRolename(String roleName) {
		Role role=this.roleDAO.findRoleByRolename(roleName);
		return role;
	}


	public List<Role> findAllRoles() {
		List<Role> roles=this.roleDAO.findAllRoles();
		return roles;
	}


	public void updateRole(Role role) {
		this.roleDAO.updateRole(role);
		
	}

}
