package com.brainbackdoor.moidakotlin.domain.content

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>