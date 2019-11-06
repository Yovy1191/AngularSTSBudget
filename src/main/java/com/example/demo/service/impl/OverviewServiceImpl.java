package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Overview;
import com.example.demo.repository.OverviewRepository;
import com.example.demo.service.IOverviewService;


@Service
public class OverviewServiceImpl implements IOverviewService {

	@Autowired
	private OverviewRepository repositoryOverview;

	@Override
	public List<Overview> listAll() {
		return repositoryOverview.findAll();
	}

	@Override
	public void save(Overview overview) {
		repositoryOverview.save(overview);
	}

	@Override
	public Overview findOne(Long id) {
		return repositoryOverview.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryOverview.deleteById(id);

	}

	@Override
	public Page<Overview> findAll(Pageable pageable) {
		return repositoryOverview.findAll(pageable);
	}

}
