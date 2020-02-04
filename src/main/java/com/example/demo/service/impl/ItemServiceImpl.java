package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Description;
import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ServicesO;
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

	public Item save(Long InvoiceId, Long ItemId,String date,  Double qte, Double price, Double subtotal,ServicesO service, Supplier supplier,  Double total,  Description description ) {
		Item item = new Item();
		item.setIdItem(new ItemId(InvoiceId,ItemId));
		item.setDate(date);
		item.setDescription(description);
        item.setQte(qte);
        item.setPrice(price);
        item.setSubtotal(subtotal);
        item.setTotal(total);
        item.setServices(service);
        item.setSupplier(supplier);
		return repositoryItem.save(item);

	}
	
	public Item saveEdit(Item item) {
		return repositoryItem.save(item);

	}


	@Override
	public void delete(ItemId itemId) {
		repositoryItem.deleteById(itemId);

	}

	public Optional<Item>  getBypk(ItemId itemId) {
		return repositoryItem.findById(itemId);
	}

	@Override
	public Page<Item> findAll(Pageable pageable) {
		return repositoryItem.findAll(pageable);
	}

	@Override
	public List<Item> findAllByOrderByDateAsc() {
		return repositoryItem.findAllByOrderByDateAsc();
	}


	

	
	
}
