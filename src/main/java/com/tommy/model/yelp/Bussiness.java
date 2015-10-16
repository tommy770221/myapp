package com.tommy.model.yelp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class Bussiness {

	private String id;
	
	private String is_claimed;
	
	private String rating;
	
	private String mobile_url;
	
	private String rating_img_url;
	
	private String review_count;
	
	private String name;
	
	private String rating_img_url_small;
	
	private String url;
	
	private String phone;
	
	private String image_url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIs_claimed() {
		return is_claimed;
	}

	public void setIs_claimed(String is_claimed) {
		this.is_claimed = is_claimed;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getMobile_url() {
		return mobile_url;
	}

	public void setMobile_url(String mobile_url) {
		this.mobile_url = mobile_url;
	}

	public String getRating_img_url() {
		return rating_img_url;
	}

	public void setRating_img_url(String rating_img_url) {
		this.rating_img_url = rating_img_url;
	}

	public String getReview_count() {
		return review_count;
	}

	public void setReview_count(String review_count) {
		this.review_count = review_count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRating_img_url_small() {
		return rating_img_url_small;
	}

	public void setRating_img_url_small(String rating_img_url_small) {
		this.rating_img_url_small = rating_img_url_small;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
}
