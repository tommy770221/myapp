package com.tommy.handler.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import com.tommy.model.Role;
import com.tommy.model.User;
import com.tommy.service.MailService;
import com.tommy.service.UserService;

public class LoginSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
	MailService mailService;
    @Autowired
    UserService service;
    
    private User userFind=null;

	@Override
     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws ServletException, IOException {
         request.getSession().setMaxInactiveInterval(60 * 60); //one hour
         System.out.println("Session set up for 60min");
		 System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		  UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        System.out.println("username: " + userDetails.getUsername());
	        System.out.println("password: " + userDetails.getPassword());
	        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) userDetails.getAuthorities();
	        for (Iterator it = authorities.iterator(); it.hasNext();) {
	            SimpleGrantedAuthority authority = (SimpleGrantedAuthority) it.next();
	            System.out.println("Role: " + authority.getAuthority());
	        }
	     userFind = (User) this.service.findUserByEmail(userDetails.getUsername());	     
	     //session 將user Id保存起來
	     request.getSession().setAttribute("userId", userFind.getId());
	     request.getSession().setAttribute("userName", userFind.getUsername());
	     request.getSession().setAttribute("userEmail", userFind.getEmail());

         super.onAuthenticationSuccess(request, response, authentication);
      }

	
}
