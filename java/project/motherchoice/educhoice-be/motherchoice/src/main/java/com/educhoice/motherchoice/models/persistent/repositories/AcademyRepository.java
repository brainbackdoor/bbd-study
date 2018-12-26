package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.HashTag;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.repositories.specifications.AcademyRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AcademyRepository extends CrudRepository<Academy, Long>, AcademyRepositoryCustom {

    Optional<Academy> findByAcademyName(String academyName);
    List<Academy> findByAcademyNameContaining(String academyName);
    Optional<Academy> findByAcademyId(long academyId);
    List<Academy> findByTagsContaining(HashTag tag);
    List<Academy> findByCourses_DateTime_StartTimeBetweenAndCourses_DateTime_EndTimeLessThan(LocalTime startTime, LocalTime endTime, LocalTime timeLimit);

}
