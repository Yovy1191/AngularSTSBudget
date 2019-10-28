package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.ISupplierService;

@Service
public class SupplierServiceImp implements ISupplierService {

	@Autowired
	private SupplierRepository repositorySupplier;
	

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

	@Override
	public Page<Supplier> listAll(Pageable pageable) {
		return repositorySupplier.findAll(pageable);
	}

	@Override
	public List<Supplier> findAll() {
		return (List<Supplier>) repositorySupplier.findAll();
	}

	@Override
	public int count() {
		return 0;
	}

}
