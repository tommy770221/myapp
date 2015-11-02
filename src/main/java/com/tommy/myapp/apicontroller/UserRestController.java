package com.tommy.myapp.apicontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tommy.model.User;
import com.tommy.service.UserService;


@RequestMapping(value = "/v1.0")
@RestController
public class UserRestController {

	@Autowired
	UserService userService;
	//apiDoc generate data start
	/**
	 * @api {get} /user/me/?access_token=:accessToken Request My information
	 * @apiName GetMe
	 * @apiGroup MyProfile
	 * @apiParam {accessToken} accessToken OAuth Access Token
	 * @apiVersion 0.0.1
	 * @apiSuccessExample {json} Success-Response:
	 *HTTP/1.1 200 OK
	 *{
     *   "id": "4028818350787517015078759a220000",
     *   "username": "tommy770221",
     *   "email": "tommy770221test@gmail.com",
     *   "createDate": 1445129984000,
     *   "updateDate": 1445129997000
     *}
	 * 
	 */
	//apiDoc generate data stop
	
	@RequestMapping(value = "/user/me", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public User me(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
			    .getAuthentication();
			String userName=authentication.getName();
		System.out.println("userId : "+userName);
		User user=userService.findUserByEmail(userName);
		
		return user;
	}
	
	
}
