package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.HashTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HashTagRepository extends CrudRepository<HashTag, Long> {

    List<HashTag> findByTitleContaining(String title);
    Optional<HashTag> findById(long id);
    
}
