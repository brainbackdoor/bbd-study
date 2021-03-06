package com.educhoice.motherchoice.models.persistent.repositories.specifications;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;

import java.util.List;
import java.util.Optional;

public interface AcademyRepositoryCustom {

    @Deprecated
    Optional<Academy> findAcademyIdCriteria(long id);

    @Deprecated
    Optional<Academy> findByAcademyNameCriteria(String name);

    Optional<List<Academy>> findAcademiesByQuery(AcademyQueryDto dto);
}
