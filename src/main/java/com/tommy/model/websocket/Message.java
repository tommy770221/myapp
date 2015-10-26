package com.tommy.model.websocket;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable{

	  private static final long serialVersionUID = 1L;
	  private String message = "";
	  private Date time = new Date();
	  
	  private DateFormat df = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
	  
		public Message() {
		}
		   
		public Message(String message) {
			this.message = message;
		}
	
		public String getTimeStr() {
		    return df.format(time);
		}

		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	
		public Date getTime() {
			return time;
		}
	
		public void setTime(Date time) {
			this.time = time;
		}
	
		public DateFormat getDf() {
			return df;
		}
	
		public void setDf(DateFormat df) {
			this.df = df;
		}
		  
	  
	
}
