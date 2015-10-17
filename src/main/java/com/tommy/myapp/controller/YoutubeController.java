package com.tommy.myapp.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.util.DateTime;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.model.yelp.Bussiness;
import com.tommy.model.yelp.Region;
import com.tommy.model.youtube.YoutubeSearch;
import com.tommy.yelp.util.YelpAPI;

@Controller
public class YoutubeController {


	private static String CLIENT_ID = "786926371085-bf1m03krhpqr3a10271i9go25m2iq90v.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "mvMOy0dNoYasE0CeK6AcvcBd";
	private static String REDIRECT_URI = "http://localhost:8080/TravelA/youtube";
	private YoutubeSearch youtubeResult;
	
	@RequestMapping(value = "/youtubeForm", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String yelpForm(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {
	
		return "youtubeForm";
	}
	
	@RequestMapping(value = "/youtubeQuery", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html; charset=utf-8")
	public String yelpAction(
			@RequestParam(value = "searchParam", required = false) String searchParam,
			@RequestParam(value = "publishBefore", required = false) String publishBefore,
			@RequestParam(value = "publishAfter", required = false) String publishAfter,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) throws ParseException {
		System.out.println("youtube query call");
		ObjectMapper mapper = new ObjectMapper();
		  if(! ("".equals(publishBefore)||publishBefore==null))
		  {
	      Date beforeDate=new SimpleDateFormat("yyyy-MM-dd").parse(publishBefore);
		  publishBefore=new DateTime(beforeDate).toStringRfc3339();
		  }
		  if(! ("".equals(publishAfter)||publishAfter==null))
		  {
		  Date afterDate=new SimpleDateFormat("yyyy-MM-dd").parse(publishAfter);
		  publishAfter=new DateTime(afterDate).toStringRfc3339();
		  System.out.println("publishAfter"+publishAfter);
		  }
		
		  
		try {
			youtubeResult=mapper.readValue(youtubeStart(searchParam,publishBefore,publishAfter), YoutubeSearch.class);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("youtubeResult", youtubeResult);
	
		return "listYoutube";
	}
	
	private String youtubeStart(String q,String before,String after) throws IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			System.out.println(" youtubeStart");
			String url ="https://www.googleapis.com/youtube/v3/search?part=snippet&order=viewCount&type=video&maxResults=10&videoDefinition=high&key=AIzaSyASWs-PeLtQ63a5KwMKPERgYSVAjpnhr00";
            if(! ("".equals(q)||q==null)){
            	url=url+"&q="+URLEncoder.encode(q);
            }
            if(! ("".equals(before)||before==null)){
            	url=url+"&publishedBefore="+URLEncoder.encode(before);
            }
            if(! ("".equals(after)||after==null)){
            	url=url+"&publishedAfter="+URLEncoder.encode(after);
            }
			
			HttpGet httpGet = new HttpGet(url);
			// get response
			System.out.println("Get URI="+httpGet.getURI());
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			
			System.out.println("getProtocolVersion=" + response.getProtocolVersion());
			System.out.println("getReasonPhrase=" + response.getStatusLine().getReasonPhrase());
			System.out.println("Response Code=" + response.getStatusLine().getStatusCode());
			System.out.println("getStatusLine=" + response.getStatusLine().toString());
			
			// get response content
			String theString = IOUtils.toString(response.getEntity().getContent(),"UTF-8");
			System.out.println("theString=" + theString);
			return theString;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new ClientProtocolException();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException();
		}
		
	}
}
