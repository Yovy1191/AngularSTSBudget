package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "BILL")
public class Bill  {
	
	@Id
	@SequenceGenerator(name = "invoiceId", sequenceName = "invoiceId", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "invoiceId")
	@JoinColumn(name = "invoiceId", updatable = false)
	public Long invoiceId;
	
	@JoinColumn(name = "date")
	private String date;
	
	@JoinColumn(name = "sub_total")
	private Double sub_total;
	
	@JoinColumn(name = "total")
	private Double total;
	
	@JoinColumn(name = "tps")
	private Double tps;
	
	@JoinColumn(name = "tvq")
	private Double tvq;
	
	@OneToOne
	@JoinColumn(name = "idCustomer")
	private Customer customer;

	
	@OneToMany(mappedBy="bill")
	public List<Item> items;
		

	public List<Item> getItems() {
		return items;
	}

	public List<Item> setItems(List<Item> items) {
		return this.items = items;
	}

	
	

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bill() {
	}

 

	
}
