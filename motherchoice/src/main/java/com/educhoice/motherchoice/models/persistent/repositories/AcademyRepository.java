package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.Academy;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AcademyRepository extends CrudRepository<Academy, Long> {

    public Optional<Academy> findByAcademyNameContaining(String academyName);
}
