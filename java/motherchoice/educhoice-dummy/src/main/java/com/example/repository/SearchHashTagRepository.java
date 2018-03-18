package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.SearchHashTag;

public interface SearchHashTagRepository extends Repository<SearchHashTag, Long> {

	List<SearchHashTag> findByValueContaining(String value);
}
