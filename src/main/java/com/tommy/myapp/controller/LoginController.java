package com.tommy.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String login(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		return "login";
	}
	
	@RequestMapping(value = "/manu", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String manu(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		return "page/manu";
	}
}
