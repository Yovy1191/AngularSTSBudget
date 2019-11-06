package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ICategoryService;


@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository repositoryCategory;

	@Override
	public List<Category> listAll() {
		return repositoryCategory.findAll();
	}

	@Override
	public void save(Category category) {
		repositoryCategory.save(category);
	}

	@Override
	public Category findOne(Long id) {
		return repositoryCategory.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryCategory.deleteById(id);

	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return repositoryCategory.findAll(pageable);
	}

}
