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
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(
			@Valid User user,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		userService.save(user);
		
		return "home";
	}

}
