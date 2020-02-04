package com.example.demo.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ITEM")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6658559180289123527L;


	@EmbeddedId
	private ItemId idItem;
	
	@JoinColumn(name = "date")
	private String date;
	
	@OneToOne
	@JoinColumn(name = "descriptionId")
	private Description description;
	
	
	@Column(name = "qte")
	private Double qte;
	
	@JoinColumn(name = "price")
	private Double price;

	@JoinColumn(name = "subtotal")
	private Double subtotal;

	public Double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}


	@JoinColumn(name = "total")
	private Double total;
		
	
	@OneToOne
	@JoinColumn(name = "serviceidService")
	public ServicesO services;
	
	@OneToOne
	@JoinColumn(name = "supplieridSupplier")
	public Supplier supplier;
	   

	@ManyToOne
    public Bill bill;
	
	@OneToOne
	@JoinColumn(name = "descriptionId")
	public Description getDescription() {
		return description;
	}


	public void setDescription(Description description) {
		this.description = description;
	}


	public ServicesO getServices() {
		return services;
	}


	public void setServices(ServicesO services) {
		this.services = services;
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



	public ItemId getIdItem() {
		return idItem;
	}


	public void setIdItem(ItemId idItem) {
		this.idItem = idItem;
	}

	
	public Bill getBill() {
		return bill;
	}


	public void setBill(Bill bill) {
		this.bill = bill;
	}

	


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}



	public Item(ItemId idItem, String date, Double qte, Double price, Double subtotal, ServicesO services, Supplier supplier, Double total,
			 Description description) {
		this.idItem.setInvoiceId(idItem.getInvoiceId());
		this.idItem.setItemId(idItem.getItemId());
		this.setDate(date);
		this.qte = qte;
		this.price = price;
		this.subtotal = subtotal;
		this.services = services;
		this.supplier = supplier;
		this.total = total;
		this.description = description;
	}

	public Item() {

	}


}
