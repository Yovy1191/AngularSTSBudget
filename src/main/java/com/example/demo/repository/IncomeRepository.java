package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Income;




@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
	
	@Query(value = "SELECT  budget.income.idIncome,budget.income.date,budget.income.typeIncomeId, budget.typeincome.nameIncome,budget.customer.firstName, budget.income.customer, budget.income.value FROM budget.income LEFT JOIN  budget.typeincome ON budget.income.typeIncomeId = budget.typeincome.idincome LEFT JOIN  budget.customer on budget.income.customer = budget.customer.idCustomer where  MONTH(date) = ?1 ", nativeQuery =    true)
	public List<Income> getIncomeMonthly(Long monthly);
	
	@Query(value = "SELECT * FROM budget.income b WHERE MONTH(b.date)  BETWEEN  month(NOW()- INTERVAL 1 MONTH)  AND  MONTH(NOW())", nativeQuery =    true)
	public List<Income> getIncomeBiannual();
	
	@Query(value = "SELECT * FROM budget.income b WHERE MONTH(b.date)  BETWEEN  month(NOW()- INTERVAL 6 MONTH)  AND  MONTH(NOW())", nativeQuery =    true)
	public List<Income> getExpensesQuartely();

}
