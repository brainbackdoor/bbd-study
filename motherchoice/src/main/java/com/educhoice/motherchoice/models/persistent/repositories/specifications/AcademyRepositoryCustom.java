package com.educhoice.motherchoice.models.persistent.repositories.specifications;

import com.educhoice.motherchoice.models.persistent.Academy;

import java.util.List;
import java.util.Optional;

public interface AcademyRepositoryCustom {

    Optional<Academy> findAcademyIdCriteria(long id);
}
