package com.tommy.model.yelp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Region {




	
	private String total;
	
	private List<Bussiness> businesses;
	


	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<Bussiness> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Bussiness> businesses) {
		this.businesses = businesses;
	}
	
}
