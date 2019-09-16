package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.IPropertyService;

@Service
public class PropertyImpl implements IPropertyService {

	@Autowired
	private PropertyRepository repositoryProperty;

	@Override
	public List<Property> listAll() {
		return repositoryProperty.findAll();
	}

	@Override
	public Property findOne(Long id) {
		return repositoryProperty.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryProperty.deleteById(id);
		
	}



	@Override
	public Property save(Property property) {
		return 	repositoryProperty.save(property);
	}



	
	

}
