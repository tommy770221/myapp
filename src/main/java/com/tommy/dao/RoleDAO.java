package com.tommy.dao;

import java.util.List;

import com.tommy.model.Role;



public interface RoleDAO {

public void saveRole(Role role);   
    
    public void removeRole(Long id);   
       
    public Role findRoleById(Long id);   
    
    public Role findRoleByRolename(String roleName); 
       
    public List<Role> findAllRoles();   
       
    public void updateRole(Role role);   
}
