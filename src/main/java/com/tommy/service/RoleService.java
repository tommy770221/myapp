package com.tommy.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tommy.model.Role;

@Service
public interface RoleService {

	    public void removeRole(Long id);   
     
	    public Role findRoleById(Long id);   
	    
	    public Role findRoleByRolename(String roleName); 
	       
	    public List<Role> findAllRoles();   
	       
	    public void updateRole(Role role); 
}
