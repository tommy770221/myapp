package com.tommy.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tommy.model.Role;
import com.tommy.model.User;
import com.tommy.service.UserService;

@Controller
@RequestMapping(value = "/regist")
public class RegistController {
	@Autowired
	UserService userService;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String registForm(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {		
		return "regist";
	}

	@RequestMapping(value = "/form",method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String registSave(
			@Valid User user,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		Role role=new Role();
		role.setName("ROLE_USER");
		List<Role> roles=new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		userService.save(user);
		return "regist";
	}
}
