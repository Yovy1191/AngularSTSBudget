package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bill;



@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
	@Query(value = "SELECT AUTO_INCREMENT FROM information_schema.TABLES   WHERE TABLE_SCHEMA = 'budget' AND TABLE_NAME = 'bill'", nativeQuery =    true)
	public Long getNextSeriesInvoiceId();
	

}
