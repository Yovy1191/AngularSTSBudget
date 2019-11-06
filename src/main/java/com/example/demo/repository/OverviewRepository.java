package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Overview;


@Repository
public interface OverviewRepository  extends JpaRepository<Overview, Long> {
}
