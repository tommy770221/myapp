package com.tommy.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tommy.model.Item;

@Service
public interface ItemService {

	public List<Item> findAll();
	
	public List<Item> findAllPagination(int page,int size);

	public void save(Item item);

	public void remove(Long itemId);

	public Item findByItemId(Long itemId);

	public void update(Item item);

}
