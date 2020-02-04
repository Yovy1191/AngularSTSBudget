package com.example.demo.model;

public class ItemWrapper {
	
	public Long InvoiceId;
	public Long ItemId ;
	public String date;
	

	public Description description;
	public Double qte;
	public Double subtotal;
	public Double price;		
	public Double total;		
	public ServicesO services;
	public Supplier supplier;
	
	
	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}


	public ServicesO getServices() {
		return services;
	}


	public void setServices(ServicesO services) {
		this.services = services;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Long getInvoiceId() {
		return InvoiceId;
	}


	public void setInvoiceId(Long invoiceId) {
		InvoiceId = invoiceId;
	}


	public Long getItemId() {
		return ItemId;
	}


	public void setItemId(Long itemId) {
		ItemId = itemId;
	}


	
	public Description getDescription() {
		return description;
	}


	public void setDescription(Description description) {
		this.description = description;
	}


	public Double getQte() {
		return qte;
	}


	public void setQte(Double qte) {
		this.qte = qte;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}




	public ItemWrapper(Long invoiceId, Long itemId, String date,  Double qte,  Double price, Double subtotal,
			ServicesO services, Supplier supplier, Double total,  Description description) {
		InvoiceId = invoiceId;
		ItemId = itemId;
		this.date =date;
		this.qte = qte;
		this.price = price;
		this.subtotal = subtotal;
		this.services = services;
		this.supplier = supplier;
		this.total = total;
		this.description = description;
	}

	public ItemWrapper(Description description, Double qte) {
		this.description = description;
		this.qte = qte;
		
	}

	

	public ItemWrapper() {
		
	}


	


}
