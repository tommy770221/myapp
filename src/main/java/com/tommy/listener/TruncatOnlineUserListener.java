package com.tommy.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tommy.service.OnlineUserService;

public class TruncatOnlineUserListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 ApplicationContext context = 
		            WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

		 OnlineUserService onlineUserService=(OnlineUserService) context.getBean("onlineUserService");
		  onlineUserService.removeAll();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 ApplicationContext context = 
		            WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

		 OnlineUserService onlineUserService=(OnlineUserService) context.getBean("onlineUserService");
		  onlineUserService.removeAll();
	}

}
