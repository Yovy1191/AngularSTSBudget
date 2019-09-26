package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ServicesOffered;
import com.example.demo.model.Supplier;



public interface IItemService {
	
	public List<Item> listAll();
	public void delete(ItemId itemId);
	Item save(Long InvoiceId, Long ItemId, String description, Double qte, Double price, Double subtotal,
			ServicesOffered service, Supplier supplier);


}
