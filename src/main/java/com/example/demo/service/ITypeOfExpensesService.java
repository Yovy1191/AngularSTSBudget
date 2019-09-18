package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TypesOfExpenses;


public interface ITypeOfExpensesService {
	
	TypesOfExpenses save(TypesOfExpenses TypesOfExpenses); 
	public void deleteById(Long id);
	public TypesOfExpenses findOne(Long id);
	public List<TypesOfExpenses> listAll();

}
