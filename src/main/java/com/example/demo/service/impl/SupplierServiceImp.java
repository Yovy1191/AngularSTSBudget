package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.ISupplierService;

@Service
public class SupplierServiceImp implements ISupplierService {

	@Autowired
	private SupplierRepository repositorySupplier;
	
	@Override
	public List<Supplier> listAll() {
		return repositorySupplier.findAll();
	}

	@Override
	public void save(Supplier supplier) {
		repositorySupplier.save(supplier);
	
		
	}

	@Override
	public Supplier findOne(Long id) {
		return repositorySupplier.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositorySupplier.deleteById(id);
		
	}

}
