package com.tommy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tommy.dao.ItemDAO;
import com.tommy.model.Item;


@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {
       
	    @PersistenceContext
	    private EntityManager manager;
	    

		public List<Item> findAllItems() {   
	        String hql = "from Item order by itemId asc";   
	        return (List<Item>) manager.createQuery(hql).getResultList();   
	    }   
	    

		public List<Item> findAllItemsPagination(int page,int size) {   
	    	System.out.println(page);
	        String hql = "from Item order by itemId asc";   
	        return (List<Item>) manager.createQuery(hql).setFirstResult((page-1)*size).setMaxResults(size).getResultList();   
	    }   

	    public Item findItemByItemId(Long itemId) {  
	    	
	        Item item = (Item)  manager.find(Item.class, itemId);   
	        return item;   
	    }   

	    public void removeItem(Long itemId) {   
	  
	    	manager.remove(findItemByItemId(itemId));   
	    }   
	  
	    public void saveItem(Item item) {   
	  
	    	manager.persist(item);   
	    }   
	    public void updateItem(Item item) {   
	  
	    	manager.merge(item);   
	    }

		public Item findItemByItemName(final String itemName) {
			final String hql = "select u from Item u where u.itemName=:itemName";
			 Item item=(Item) manager.createQuery(hql).setParameter("itemName", itemName).getSingleResult(); 				
			return item;
		}

	
	  

}
