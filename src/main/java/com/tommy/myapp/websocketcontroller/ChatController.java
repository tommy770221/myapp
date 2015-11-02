package com.tommy.myapp.websocketcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tommy.model.websocket.Message;


@Controller
public class ChatController {

	@Autowired 
	private SimpMessagingTemplate simpMessagingTemplate;  
	
	
	@MessageMapping("/fleet/{fleetId}/driver/{driverId}")
	public void simple(@DestinationVariable("fleetId") String fleetId, @DestinationVariable("driverId") String driverId,Message message) {
		List<Message> messages=new ArrayList<Message>();
		messages.add(message);
		System.out.println("send message");
		simpMessagingTemplate.convertAndSend("/topic/fleet/"+fleetId+"/"+driverId, messages);
	}
	
	 /**
	   * Serve the main page, view will resolve to /WEB-INF/views/websocket.jsp
	   */
	  @RequestMapping(value = "/chat", method = RequestMethod.GET)
	  public String home(HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse, Model model) {
		  HttpSession session=httpServletRequest.getSession();
		  String userId=(String) session.getAttribute("userId");
		  model.addAttribute("userId",userId);
		  
	    return "chat";
	  }
}
