package com.educhoice.motherchoice.models.persistent.repositories.specifications.implementations;

import com.educhoice.motherchoice.models.persistent.*;
import com.educhoice.motherchoice.models.persistent.Course.CoursesClassification.SpecifiedCoursesClassification;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress_;
import com.educhoice.motherchoice.models.persistent.repositories.specifications.AcademyRepositoryCustom;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AcademyRepositoryImpl implements AcademyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Deprecated
    public Optional<Academy> findAcademyIdCriteria(long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> c = query.from(Academy.class);

        query.select(c).where(cb.equal(c.get(Academy_.academyId), id));

        TypedQuery<Academy> resultQuery = entityManager.createQuery(query);

        return Optional.ofNullable(resultQuery.getSingleResult());
    }

    @Override
    @Transactional
    @Deprecated
    public Optional<Academy> findByAcademyNameCriteria(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> c = query.from(Academy.class);

        query.select(c).where(cb.equal(c.get(Academy_.academyName), name));

        TypedQuery<Academy> resultQuery = entityManager.createQuery(query);

        return Optional.ofNullable(resultQuery.getSingleResult());
    }

    @Override
    @Transactional
    public Optional<List<Academy>> findAcademiesByQuery(AcademyQueryDto dto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        List<Predicate> conditions = Lists.newArrayList();
        ParameterExpression<LocalTime> startTime = cb.parameter(LocalTime.class);
        ParameterExpression<LocalTime> endTime = cb.parameter(LocalTime.class);
        ParameterExpression<Grades.SpecifiedGrades> grade = cb.parameter(Grades.SpecifiedGrades.class);
        ParameterExpression<SpecifiedCoursesClassification> course = cb.parameter(SpecifiedCoursesClassification.class);

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> academyRoot = query.from(Academy.class);


        Join<Academy, Course> courses = academyRoot.join("courses");
        Join<Academy, AcademyAddress> addresses = academyRoot.join("address");
        Join<Course, DateTime> datetimes = courses.join("dateTime");


        if(dto.isCarAvailable() == true) {
            conditions.add(cb.equal(academyRoot.get(Academy_.carAvailable), dto.isCarAvailable()));
        }

        if(dto.getGrade() != null) {
            conditions.add(cb.equal(courses.get(Course_.grades).as(Grades.SpecifiedGrades.class), grade));
        }

        if(StringUtils.hasText(dto.getSubject())) {
            conditions.add(cb.equal(courses.get(Course_.coursesClassification).as(SpecifiedCoursesClassification.class), course));
        }

        if(dto.getTime() != null) {
            conditions.add(cb.between(datetimes.get(DateTime_.startTime).as(LocalTime.class), startTime, endTime));
        }

        if(StringUtils.hasText(dto.getAddress())) {
            conditions.add(cb.like(addresses.get(AcademyAddress_.address).as(String.class), "%" + dto.getAddress() + "%"));
        }

        if(dto.getTime().generateDateTimefromDto() != null) {
            conditions.add(datetimes.get("day").as(String.class).in(dto.getTime().generateDateTimefromDto().stream().map(d -> d.getDay()).map(day -> day.name()).collect(Collectors.toList())));
        }

        query.where(conditions.toArray(new Predicate[]{}));
        TypedQuery<Academy> academyTypedQuery = entityManager.createQuery(query);

        if(dto.getTime() != null) {
            academyTypedQuery.setParameter(startTime, dto.getTime().generateStartTime());
            academyTypedQuery.setParameter(endTime, dto.getTime().generateEndTime());
        }

        if(dto.getGrade() != null) {
            academyTypedQuery.setParameter(grade, dto.getGrade());
        }

        if(dto.generateSpecifiedCourse() != null) {
            academyTypedQuery.setParameter(course, dto.generateSpecifiedCourse());
        }

        List<Academy> resultList = academyTypedQuery.getResultList();

        if(resultList.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(academyTypedQuery.getResultList());
    }
}
