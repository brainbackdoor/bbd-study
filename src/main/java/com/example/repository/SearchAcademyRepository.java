package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.SearchAcademy;

public interface SearchAcademyRepository extends Repository<SearchAcademy, Long> {

	List<SearchAcademy> findByValueContaining(String value);

}
