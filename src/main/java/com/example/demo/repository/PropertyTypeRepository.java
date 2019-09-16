package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TypeProperty;

@Repository
public interface PropertyTypeRepository  extends JpaRepository<TypeProperty, Long> {

}
