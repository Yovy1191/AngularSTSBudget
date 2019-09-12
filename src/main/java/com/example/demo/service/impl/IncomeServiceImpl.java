package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Income;
import com.example.demo.repository.IncomeRepository;
import com.example.demo.service.IIncomeService;

@Service
public class IncomeServiceImpl implements IIncomeService {

	@Autowired
	private IncomeRepository repositoryIncome;
	
	
	@Override
	public List<Income> listAll() {
		return repositoryIncome.findAll();

	}

	@Override
	public void save(Income income) {
		repositoryIncome.save(income);
	}

	@Override
	public Income findOne(Long id) {
		return repositoryIncome.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryIncome.deleteById(id);
		
	}
	



}
