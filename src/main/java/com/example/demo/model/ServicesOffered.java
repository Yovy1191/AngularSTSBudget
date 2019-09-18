package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE")
public class ServicesOffered  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idService")
	private Long idService;
	
	@Column(name = "nameService")
	private String nameService;
	
	public ServicesOffered() {
		
	}
	
	public Long getIdService() {
		return idService;
	}

	public void setIdService(Long idService) {
		this.idService = idService;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}
}
