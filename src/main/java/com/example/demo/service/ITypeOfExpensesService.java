package com.example.demo.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.model.TypesOfExpenses;


public interface ITypeOfExpensesService {
	
	TypesOfExpenses save(TypesOfExpenses TypesOfExpenses); 
	public void deleteById(Long id);
	Optional<TypesOfExpenses> findById(Long id);
	public List<TypesOfExpenses> listAll();

}
