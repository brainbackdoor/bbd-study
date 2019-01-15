package com.brainbackdoor.moidakotlin.domain.content

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Item, Long>