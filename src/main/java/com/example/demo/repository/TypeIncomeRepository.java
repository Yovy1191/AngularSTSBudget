package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.model.TypeIncome;

@Repository
public interface TypeIncomeRepository extends JpaRepository<TypeIncome, Long> {
	
	

}
