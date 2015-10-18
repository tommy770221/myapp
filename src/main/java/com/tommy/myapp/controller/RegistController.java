package com.tommy.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tommy.model.Role;
import com.tommy.model.User;
import com.tommy.service.MailService;
import com.tommy.service.RoleService;
import com.tommy.service.UserService;

@Controller
@RequestMapping(value = "/regist")
public class RegistController {
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	RoleService roleService;

	
	
	@RequestMapping(method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String registForm(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {		
		return "regist";
	}

	@RequestMapping(value = "/form",method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public String registSave(
			@Valid User user,
			HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Role role=roleService.findRoleByRolename("ROLE_USER");
		List<Role> roles=new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String hashPassword = md5.encodePassword(user.getPassword(), user.getEmail());
	    System.out.println(hashPassword);
	    user.setPassword(hashPassword);
		userService.save(user);
		ResourceBundle resb2 = ResourceBundle.getBundle("app", Locale.getDefault());
        System.out.println(resb2.getString("app.domain")); 
		mailService.sendMail("tommy770221test@gmail.com", user.getEmail().trim(),
				"恭喜加入會員", "<a href=\""+resb2.getString("app.domain")+"/regist/activeUser?activeCode="+user.getId()+"\">影點選此處啟用帳號</a>");
		request.setAttribute("register_success","congratulation Please Active your account and login!");		
		System.out.println("加入成功");
		return "login";
	}
	
	@RequestMapping(value = "/activeUser",method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String activeUser(
			@RequestParam(value = "activeCode", required = true) String userId,
			HttpServletRequest request,
			HttpServletResponse response, Model model) {
	    User user=userService.findByUserId(userId);
	    user.setEnabled(true);
	    userService.update(user);
		System.out.println("啟用成功");
		return "home";
	}
	
	
}
