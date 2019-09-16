package com.example.demo.service;

import java.util.List;


import com.example.demo.model.TypeProperty;

public interface IPropertyTypeService {

	public List<TypeProperty> listAll();
	public TypeProperty save(TypeProperty typeproperty); 
	public TypeProperty findOne(Long id);
	public void delete(Long id);

}
