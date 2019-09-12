package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Supplier;

public interface ISupplierService {

	public List<Supplier> listAll();
	public void save(Supplier supplier); 
	public Supplier findOne(Long id);
	public void delete(Long id);

}
