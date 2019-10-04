package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ServicesOffered;
import com.example.demo.model.Supplier;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private ItemRepository repositoryItem;

	
	@Override
	public List<Item> listAll() {
		return repositoryItem.findAll();

	}

	public Item save(Long InvoiceId, Long ItemId, String description, Double qte, Double price, Double total, ServicesOffered service, Supplier supplier ) {
		Item item = new Item();
		item.setIdItem(new ItemId(InvoiceId,ItemId));
		item.setDescription(description);
        item.setQte(qte);
        item.setPrice(price);
        item.setTotal(total);
        item.setServices(service);
        item.setSupplier(supplier);
		return repositoryItem.save(item);

	}

	@Override
	public void delete(ItemId itemId) {
		repositoryItem.deleteById(itemId);

	}

	public Optional<Item>  getBypk(ItemId itemId) {
		return repositoryItem.findById(itemId);
	}

	

	
	
}
