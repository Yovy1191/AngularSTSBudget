package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "OVERVIEW")
public class Overview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idOverview")
	public long idOverview;
	
	@NotNull
	@Size(min=2, max=40, message="First name must be between 2 and 40 characters")
	@JoinColumn(name = "nameOverview")
	private String  nameOverview;
	
	
	private Double valueOverview;

	public Double getValueOverview() {
		return valueOverview;
	}

	public void setValueOverview(Double valueOverview) {
		this.valueOverview = valueOverview;
	}

	public long getIdOverview() {
		return idOverview;
	}

	public void setIdOverview(long idOverview) {
		this.idOverview = idOverview;
	}

	public String getNameOverview() {
		return nameOverview;
	}

	public void setNameOverview(String nameOverview) {
		this.nameOverview = nameOverview;
	}

	

	public Overview(long idOverview, String nameOverview, Double valueOverview) {
		super();
		this.idOverview = idOverview;
		this.nameOverview = nameOverview;
		this.valueOverview = valueOverview;
	}

	public Overview() {
	}
	
	
	
	

}
