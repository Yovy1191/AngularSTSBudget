package com.example.demo.model;

public class ItemWrapper {
	
	public Long InvoiceId;
	public Long ItemId ;
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


	public ItemWrapper(Long invoiceId, Long itemId, Description description, Double qte, Double subtotal, Double price,
			Double total, ServicesO services, Supplier supplier) {
		super();
		InvoiceId = invoiceId;
		ItemId = itemId;
		this.description = description;
		this.qte = qte;
		this.subtotal = subtotal;
		this.price = price;
		this.total = total;
		this.services = services;
		this.supplier = supplier;
	}


	public ItemWrapper() {
		
	}


	


}
