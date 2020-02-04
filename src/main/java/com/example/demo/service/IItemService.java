package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Description;
import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ServicesO;
import com.example.demo.model.Supplier;



public interface IItemService {
	
	Page<Item> findAll(Pageable pageable);
	public List<Item> listAll();
	public void delete(ItemId itemId);
	Item save(Long InvoiceId, Long ItemId, String date, Double qte, Double price,  Double subtotal, ServicesO service, Supplier supplier, Double total,
			  Description description);
	Item saveEdit(Item item); 
	public Object getBypk(ItemId idItem);
	List<Item> findAllByOrderByDateAsc();

}
