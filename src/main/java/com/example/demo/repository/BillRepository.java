package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bill;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;




@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
	
//	@Query(value = "SELECT AUTO_INCREMENT FROM information_schema.TABLES   WHERE TABLE_SCHEMA = 'budget' AND TABLE_NAME = 'invoiceid'", nativeQuery =    true)
//	public Long getNextSeriesInvoiceId();
	
	@Query(value = "SELECT * FROM budget.invoiceid", nativeQuery =    true)
	public Long getNextSeriesInvoiceId();
	
	
	@Query(value = "SELECT * FROM budget.bill where MONTH(date) = ?1", nativeQuery =    true)
	public List<Bill> getBillMonthly(Long monthly);
	
//	@Query(value = "SELECT * FROM budget.bill where MONTH(date) = ?1", nativeQuery =    true)
//	public List<Bill> getExpensesMonthly(Long monthly);
	
//	@Query(value = "SELECT * FROM budget.item" , nativeQuery =    true)
	@Query(value = "SELECT CAST(i.InvoiceId AS UNSIGNED ) as InvoiceId, CAST(i.ItemId AS UNSIGNED) as ItemId, i.qte, i.price, i.serviceidService, i.supplieridSupplier, i.total, i.descriptionId from budget.item i" , nativeQuery =    true)
	public List<ItemWrapper> getItemMonthly();
	
	@Query(value = "SELECT * FROM budget.bill b WHERE MONTH(b.date)  BETWEEN  month(NOW()- INTERVAL 1 MONTH)  AND  MONTH(NOW())", nativeQuery =    true)
	public List<Bill> getExpensesBiannual();
	
	@Query(value = "SELECT * FROM budget.bill b WHERE MONTH(b.date)  BETWEEN  month(NOW()- INTERVAL 6 MONTH)  AND  MONTH(NOW())", nativeQuery =    true)
	public List<Bill> getExpensesQuartely();
	
	

}
