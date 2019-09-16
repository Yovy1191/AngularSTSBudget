package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Property;

public interface IPropertyService {

	public List<Property> listAll();
	Property save(Property property); 
	public Property findOne(Long id);
	public void delete(Long id);

}
