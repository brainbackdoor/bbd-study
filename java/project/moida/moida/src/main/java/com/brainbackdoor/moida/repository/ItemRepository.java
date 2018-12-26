package com.brainbackdoor.moida.repository;

import com.brainbackdoor.moida.model.content.Item;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemRepository {
    List<Item> findById(Long id);
}
