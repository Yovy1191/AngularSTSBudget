package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TypeIncome;
import com.example.demo.repository.TypeIncomeRepository;
import com.example.demo.service.ITypeIncomeService;

@Service
public class TypeIncomeServiceImpl implements ITypeIncomeService {

	@Autowired
	private TypeIncomeRepository repositoryTypeIncome;

	
	@Override
	public TypeIncome save(TypeIncome TypeIncome) {
		return repositoryTypeIncome.save(TypeIncome);
		
	}

	
	@Override
	public void deleteById(Long id) {
		repositoryTypeIncome.deleteById(id);
		
	}

	

	@Override
	public List<TypeIncome> listAll() {
	return repositoryTypeIncome.findAll();
	}


	@Override
	public TypeIncome findOne(Long id) {
	 return repositoryTypeIncome.findById(id).get();
	}


	

}
