package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.DummySearchAcademy;

public interface DummySearchAcademyRepository extends Repository<DummySearchAcademy, Long> {

	List<DummySearchAcademy> findByacademyNameContaining(String academyName);

	List<DummySearchAcademy> findAll();
}
