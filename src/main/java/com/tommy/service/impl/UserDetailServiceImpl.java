package com.tommy.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tommy.dao.UserDAO;
import com.tommy.model.Role;
import com.tommy.model.User;

public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDao;		
	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		User user=null;
		
		 try {
	            user = userDao.findUserByEmail(email);
	            if (user == null) {
	                throw new UsernameNotFoundException("user not found");
	            }
	        } catch (Exception e) {
	            throw new EntityNotFoundException(e.getCause().getMessage());
	        }
	        String uname = user.getEmail();
	        String password = user.getPassword();
	        boolean enabled = user.getEnabled();
	        boolean accountNonExpired = Boolean.TRUE;
	        boolean credentialsNonExpired = Boolean.TRUE;
	        boolean accountNonLocked = Boolean.TRUE;
	        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	        for (Iterator it = user.getRoles().iterator(); it.hasNext();) {
	            Role role = (Role) it.next();
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	        return new org.springframework.security.core.userdetails.User(
	                uname, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	  
	}

}
