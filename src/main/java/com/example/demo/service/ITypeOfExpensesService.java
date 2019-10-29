package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.TypesOfExpenses;


public interface ITypeOfExpensesService {
	
	TypesOfExpenses save(TypesOfExpenses TypesOfExpenses); 
	public void deleteById(Long id);
	public TypesOfExpenses findOne(Long id);
	Page<TypesOfExpenses> findAll(Pageable pageable);
	public List<TypesOfExpenses> listAll();

}
