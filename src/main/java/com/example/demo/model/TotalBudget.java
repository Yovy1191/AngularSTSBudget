package com.example.demo.model;



public class TotalBudget  {
	

	public Long invoiceId;
	

	public String date;
	

	public String customer;
	

	private String typeexpenses;


	private Double sub_total;
	
	
	private Double total;


	private Double tps;
	

	private Double tvq;
	
	
	private boolean taxesIncluded = false;
	
	
	private String nameTypeExpense ;
	

	private Long idCategory;
	

	private String categoryName;
	

	public long descriptionId;
	

	private String descriptionName;


	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTypeexpenses() {
		return typeexpenses;
	}

	public void setTypeexpenses(String typeexpenses) {
		this.typeexpenses = typeexpenses;
	}

	public Double getSub_total() {
		return sub_total;
	}

	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTps() {
		return tps;
	}

	public void setTps(Double tps) {
		this.tps = tps;
	}

	public Double getTvq() {
		return tvq;
	}

	public void setTvq(Double tvq) {
		this.tvq = tvq;
	}

	public boolean isTaxesIncluded() {
		return taxesIncluded;
	}

	public void setTaxesIncluded(boolean taxesIncluded) {
		this.taxesIncluded = taxesIncluded;
	}

	public String getNameTypeExpense() {
		return nameTypeExpense;
	}

	public void setNameTypeExpense(String nameTypeExpense) {
		this.nameTypeExpense = nameTypeExpense;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(long descriptionId) {
		this.descriptionId = descriptionId;
	}

	public String getDescriptionName() {
		return descriptionName;
	}

	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}

	

	
	public TotalBudget(Long invoiceId, String date, String customer, String typeexpenses, Double sub_total,
			Double total, Double tps, Double tvq, boolean taxesIncluded, String nameTypeExpense, Long idCategory,
			String categoryName, long descriptionId, String descriptionName) {
		super();
		this.invoiceId = invoiceId;
		this.date = date;
		this.customer = customer;
		this.typeexpenses = typeexpenses;
		this.sub_total = sub_total;
		this.total = total;
		this.tps = tps;
		this.tvq = tvq;
		this.taxesIncluded = taxesIncluded;
		this.nameTypeExpense = nameTypeExpense;
		this.idCategory = idCategory;
		this.categoryName = categoryName;
		this.descriptionId = descriptionId;
		this.descriptionName = descriptionName;
	}

	public TotalBudget() {


	}





	
}
