package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Description;


@Repository
public interface DescriptionRepository  extends JpaRepository<Description, Long> {



}
