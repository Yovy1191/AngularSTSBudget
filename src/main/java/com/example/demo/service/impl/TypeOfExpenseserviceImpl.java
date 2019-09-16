package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TypesOfExpenses;
import com.example.demo.repository.TypeOfExpensesRepository;
import com.example.demo.service.ITypeOfExpensesService;

@Service
public class TypeOfExpenseserviceImpl implements ITypeOfExpensesService {

	@Autowired
	private TypeOfExpensesRepository repositoryTypeOfExpenses;

	@Override
	public TypesOfExpenses save(TypesOfExpenses TypesOfExpenses) {
     return repositoryTypeOfExpenses.save(TypesOfExpenses);
  
	}

	@Override
	public void deleteById(Long id) {
		repositoryTypeOfExpenses.deleteById(id);
		
	}

	@Override
	public Optional<TypesOfExpenses> findById(Long id) {
		return repositoryTypeOfExpenses.findById(id);
	}

	@Override
	public List<TypesOfExpenses> listAll() {
		return repositoryTypeOfExpenses.findAll();
	}

	


	

}
