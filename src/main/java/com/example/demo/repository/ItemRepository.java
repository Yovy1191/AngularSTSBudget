package com.example.demo.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Item;
import com.example.demo.model.ItemId;



@Repository
public interface ItemRepository extends JpaRepository<Item, ItemId> {

	@Query(value = "SELECT * FROM budget.item where date is not null  order by date", nativeQuery =    true)
	public List<Item> getItemsDate();
	
	List<Item> findAllByOrderByDateAsc();

}
