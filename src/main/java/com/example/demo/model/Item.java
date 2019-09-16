package com.example.demo.model;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ITEM")
public class Item  {

	@EmbeddedId
	private ItemId idItem;
	
	@Column(name="description")
	private String description;
	
	@Column(name = "qte")
	private Double qte;
	
	@JoinColumn(name = "price")
	private Double price;


	@JoinColumn(name = "total")
	private Double total;
		
	
	@OneToOne
	@JoinColumn(name = "serviceidService")
	public ServicesOffered services;
	
	@OneToOne
	@JoinColumn(name = "supplieridSupplier")
	public Supplier supplier;
	   

	@ManyToOne
    public Bill bill;
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ServicesOffered getServices() {
		return services;
	}


	public void setServices(ServicesOffered services) {
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

	
	
	public Item() {


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


}