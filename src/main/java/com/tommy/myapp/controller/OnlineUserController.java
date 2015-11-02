package com.tommy.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tommy.model.OnlineUser;
import com.tommy.service.OnlineUserService;

@RestController
public class OnlineUserController {
    @Autowired
	OnlineUserService	onlineUserService;
	@RequestMapping(value = "/userOnline", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<OnlineUser> login(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		List<OnlineUser> onlineUsers=null;
		
		 try {
			onlineUsers=onlineUserService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onlineUsers;
	}
}
