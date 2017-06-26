package com.tommy.myapp.controller;




import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.model.yelp.Bussiness;
import com.tommy.model.yelp.Region;
import com.tommy.yelp.util.YelpAPI;

@Controller
public class YelpController {

	private static final String CONSUMER_KEY = "4BAVWilwkeheTpwKfl-uKQ";
	private static final String CONSUMER_SECRET = "N4ejLJn8n2khvvC1dIPWU_9xJXw";
	private static final String TOKEN = "pNNi8vVHNXyyViZJE9qJpHRSU7qwgwGE";
	private static final String TOKEN_SECRET = "zJ1-9TuEiHd0re86sADPlkbRyqg";
	private Region region=new Region();
	
	@RequestMapping(value = "/yelpForm", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String yelpForm(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
	
		return "yelpForm";
	}
	
	@RequestMapping(value = "/yelpAction", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html; charset=utf-8")
	public String yelpAction(
			@RequestParam(value = "yelpAddress", required = false) String yelpAddress,
			@RequestParam(value = "yelpSubject", required = false) String yelpSubject,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
		
		YelpAPI yelpApi = new YelpAPI(CONSUMER_KEY, CONSUMER_SECRET, TOKEN,
				TOKEN_SECRET);
		String searchResponseJSON = yelpApi.searchForBusinessesByLocation(
				yelpSubject, yelpAddress);
		System.out.println(searchResponseJSON);
		ObjectMapper mapper = new ObjectMapper();
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
		// false);
		try {
			region = mapper.readValue(searchResponseJSON, Region.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(region!=null & region.getBusinesses()!=null){
			for (Bussiness bussiness : region.getBusinesses()) {
				System.out.println(bussiness.getId());
				System.out.println(bussiness.getUrl());
			}
		}
		model.addAttribute("region", region);
	
		return "listYelp";
	}
}
