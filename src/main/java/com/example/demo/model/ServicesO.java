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
@Table(name = "SERVICESO")
public class ServicesO  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idservice")
	private long idservice;
	
	@NotNull
	@Size(min=2, max=100, message="First name must be between 2 and 100 characters")
	@JoinColumn(name = "nameService")
	private String nameService;
	
	public ServicesO() {
		
	}

	public long getIdservice() {
		return idservice;
	}

	public void setIdservice(long idservice) {
		this.idservice = idservice;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}
	


	
}
