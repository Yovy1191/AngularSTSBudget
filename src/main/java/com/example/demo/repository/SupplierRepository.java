package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Supplier;

@Repository
public interface SupplierRepository  extends PagingAndSortingRepository<Supplier, Long> {

	
}
