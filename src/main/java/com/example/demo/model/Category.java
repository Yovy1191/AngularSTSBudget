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
@Table(name = "CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idCategory")
	public Long idCategory;
	
	@NotNull
	@Size(min=2, max=40, message="First name must be between 2 and 40 characters")
	@JoinColumn(name = "categoryName")
	private String categoryName;

		
	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

	public Category(long idCategory,
			@NotNull @Size(min = 2, max = 40, message = "First name must be between 2 and 40 characters") String categoryName) {
		this.idCategory = idCategory;
		this.categoryName = categoryName;
	}

	public Category() {
	}
	
	
	

}
