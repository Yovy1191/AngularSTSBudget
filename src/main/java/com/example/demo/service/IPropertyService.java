package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Property;

public interface IPropertyService {

	Page<Property> findAll(Pageable pageable);
	public List<Property> listAll();
	Property save(Property property); 
	public Property findOne(Long id);
	public void delete(Long id);

}
