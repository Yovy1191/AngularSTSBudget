package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bill;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.TotalBudget;




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
	
	@Query(value = "select  CAST(x.invoiceId AS UNSIGNED) as InvoiceId,  CAST(x.date AS UNSIGNED) as date,  CAST(x.idCustomer AS UNSIGNED) as idCustomer, CAST(x.typeexpensesId AS UNSIGNED) as typeexpensesId,CAST(x.sub_total AS DOUBLE) as subtotal,CAST(x.total AS DOUBLE) as total,CAST(x.tps AS DOUBLE) as tps, CAST(x.tvq AS DOUBLE) as tvqs, x.taxesIncluded, x.nameTypeExpense, CAST(c.idCategory AS UNSIGNED) as idCategory,  CAST(c.categoryName AS UNSIGNED) as categoryName,  CAST(test.descriptionId AS UNSIGNED) as descriptionId, CAST(test.descriptionName AS UNSIGNED) as descriptionName from (SELECT  * 	FROM budget.bill b 	INNER JOIN budget.typeofexpenses t ON b.typeexpensesId = t.idExpense where month(b.date) = 1 ) as x inner join budget.category c  on  c.idCategory = X.categoryId inner join (select d.invoiceId, d.descriptionId, s.descriptionName from (SELECT  b.invoiceId , i.descriptionId 	FROM budget.bill b  INNER JOIN budget.item i  on  i.InvoiceId = b.invoiceId  where month(b.date) = 1 ) as d inner join budget.description s on s.descriptionId = d.descriptionId) as test  on  test.invoiceId = x.invoiceId", nativeQuery =    true)
	public List<TotalBudget> getExpenses();
	
	

}
