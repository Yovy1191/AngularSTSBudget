package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.TypeIncome;


public interface ITypeIncomeService {
	
	TypeIncome save(TypeIncome TypeIncome); 
	public void deleteById(Long id);
	Optional<TypeIncome> findById(Long id);
	public List<TypeIncome> listAll();

}
