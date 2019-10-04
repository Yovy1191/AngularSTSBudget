package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Income;
import com.example.demo.repository.IncomeRepository;
import com.example.demo.service.IIncomeService;

@Service
public class IncomeImpl implements IIncomeService {

	@Autowired
	private IncomeRepository repositoryIncome;

	@Override
	public List<Income> listAll() {
		return repositoryIncome.findAll();
	}

	@Override
	public Income save(Income income) {
		return repositoryIncome.save(income) ;
	}

	@Override
	public Optional<Income> findOne(Long id) {
		return repositoryIncome.findById(id);
	}

	@Override
	public void delete(Long id) {
		repositoryIncome.deleteById(id);

	}

	@Override
	public List<Income> getIncomeMonthly(Long monthly) {
		return repositoryIncome.getIncomeMonthly(monthly);
	}

	@Override
	public Double TotalIncomeBudget(List<Income> incomeTotal) {
		int total = 0;	
		for (Income item : incomeTotal) {
			    total = total + item.getValue().intValue();	}
	return  Double.valueOf(total);
	}

	@Override
	public List<Income> getIncomeBiannual() {
		return repositoryIncome.getIncomeBiannual();
	}

	@Override
	public List<Income> getExpensesQuartely() {
		return repositoryIncome.getExpensesQuartely();
	}

	

}
