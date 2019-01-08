package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

//public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository<Post> {
public interface PostRepository extends MyRepository<Post, Long> {
}
