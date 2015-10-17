package com.tommy.dao;

import java.util.List;

import com.tommy.model.Item;

public interface ItemDAO {

	public void saveItem(Item item);   
    
    public void removeItem(Long itemId);   
       
    public Item findItemByItemId(Long itemId);   
    
    public Item findItemByItemName(String itemName); 
       
    public List<Item> findAllItems();   
       
    public void updateItem(Item Item);  
    
    public List<Item> findAllItemsPagination(int page,int size);
	
	
}
