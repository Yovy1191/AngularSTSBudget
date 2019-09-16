package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TypeProperty;
import com.example.demo.repository.PropertyTypeRepository;
import com.example.demo.service.IPropertyTypeService;

@Service
public class PropertyTypeImpl implements IPropertyTypeService {

	@Autowired
	private PropertyTypeRepository repositoryPropertyType;

	@Override
	public List<TypeProperty> listAll() {
		return repositoryPropertyType.findAll();
	}

	@Override
	public TypeProperty save(TypeProperty typeproperty) {
		return repositoryPropertyType.save(typeproperty);
	}

		
	@Override
	public TypeProperty findOne(Long id) {
		return repositoryPropertyType.findById(id).get();
	}


	@Override
	public void delete(Long id) {
		repositoryPropertyType.deleteById(id);

	}

	

}
