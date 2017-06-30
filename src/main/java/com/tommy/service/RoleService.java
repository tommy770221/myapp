package com.tommy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tommy.model.hibernate.Role;

@Service
public interface RoleService {

	    public void removeRole(Long id);   
     
	    public Role findRoleById(Long id);   
	    
	    public Role findRoleByRolename(String roleName); 
	       
	    public List<Role> findAllRoles();   
	       
	    public void updateRole(Role role); 
}
