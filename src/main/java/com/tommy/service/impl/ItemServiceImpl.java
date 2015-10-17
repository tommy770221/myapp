package com.tommy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tommy.dao.ItemDAO;
import com.tommy.model.Item;
import com.tommy.service.ItemService;

public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDAO itemDao;

	public void remove(Long id) {
		this.itemDao.removeItem(id);
	}

	public List<Item> findAll() {
		return this.itemDao.findAllItems();
	}

	public List<Item> findAllPagination(int page,int size) {
		
		return this.itemDao.findAllItemsPagination(page, size);
	}
	
	public Item findByItemId(Long itemId) {

		return this.itemDao.findItemByItemId(itemId);
	}

	public void save(Item item) {
		this.itemDao.saveItem(item);
	}

	public void update(Item item) {
		this.itemDao.updateItem(item);
	}

	

	

}
