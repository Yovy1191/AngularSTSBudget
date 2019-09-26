package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;


import org.springframework.data.domain.Pageable;

import com.example.demo.model.Supplier;

public interface ISupplierService  {

	Page<Supplier> listAll(Pageable pageable);
	List<Supplier> findAll();
	public void save(Supplier supplier); 
	public Supplier findOne(Long id);
	public void delete(Long id);
	int count();
}
