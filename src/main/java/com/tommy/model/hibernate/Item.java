package com.tommy.model.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable{
	@Id
	@GeneratedValue
	@Column
	private   Long itemId;	
	@Column
	private String itemName;
	@Column
	private String youtubeUrl;
	@Column
	private String intro;
	@Column
	private String goal;	
	@Column
	private double price;
	@OneToMany
    private List<ItemGroup> itemGroup;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<ItemGroup> getItemGroup() {
		return itemGroup;
	}
	public void setItemGroup(List<ItemGroup> itemGroup) {
		this.itemGroup = itemGroup;
	}
	
	
	
	
	
}
