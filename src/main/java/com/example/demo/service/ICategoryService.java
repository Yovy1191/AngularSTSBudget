package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Category;

public interface ICategoryService {

	Page<Category> findAll(Pageable pageable);
	public List<Category> listAll();
	public void save(Category category); 
	public Category findOne(Long id);
	public void delete(Long id);

}
