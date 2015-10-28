package com.tommy.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tommy.service.OnlineUserService;

public class OnlineUserListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if(se.getSession().getAttribute("userId")!=null){
			  ApplicationContext context = 
			            WebApplicationContextUtils.getWebApplicationContext(
			                se.getSession().getServletContext()
			            );
			  OnlineUserService onlineUserService=(OnlineUserService) context.getBean("onlineUserService");
			  onlineUserService.remove(se.getSession().getId());
		}

	}

}
