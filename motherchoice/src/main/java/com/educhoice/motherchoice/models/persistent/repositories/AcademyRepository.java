package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.HashTag;
import com.educhoice.motherchoice.models.persistent.Academy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AcademyRepository extends CrudRepository<Academy, Long> {

    Optional<Academy> findByAcademyNameContaining(String academyName);
    List<Academy> findByTagsContaining(HashTag tag);
}
