package com.educhoice.motherchoice.models.persistent.repositories.specifications.implementations;

import com.educhoice.motherchoice.models.persistent.*;
import com.educhoice.motherchoice.models.persistent.repositories.specifications.AcademyRepositoryCustom;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;
import com.google.common.collect.Lists;
import org.apache.tomcat.jni.Local;
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
    public Optional<Academy> findByAcademyNameCriteria(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> c = query.from(Academy.class);

        query.select(c).where(cb.equal(c.get(Academy_.academyName), name));

        TypedQuery<Academy> resultQuery = entityManager.createQuery(query);

        return Optional.ofNullable(resultQuery.getSingleResult());
    }

    public Optional<List<Academy>> findAcademiesByQuery(AcademyQueryDto dto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        List<Predicate> conditions = Lists.newArrayList();
        ParameterExpression<LocalTime> startTime = cb.parameter(LocalTime.class);
        ParameterExpression<LocalTime> endTime = cb.parameter(LocalTime.class);
        ParameterExpression<Grades.SpecifiedGrades> grade = cb.parameter(Grades.SpecifiedGrades.class);
        ParameterExpression<DateTime.WeekDays> daysParam = cb.parameter(DateTime.WeekDays.class);

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> academyRoot = query.from(Academy.class);


        Join<Academy, Course> courses = academyRoot.join("courses");
        Join<Course, DateTime> datetimes = courses.join("dateTime");

        conditions.add(cb.between(datetimes.get(DateTime_.startTime).as(LocalTime.class), startTime, endTime));

        if(dto.isCarAvailable() == true) {
            conditions.add(cb.equal(academyRoot.get(Academy_.carAvailable), dto.isCarAvailable()));
        }

        if(dto.getGrade() != null) {
            conditions.add(cb.equal(courses.get(Course_.grades).as(Grades.SpecifiedGrades.class), grade));
        }

        if(StringUtils.hasText(dto.getSubject())) {

        }

        if(dto.getTime() != null) {
            conditions.add(cb.between(datetimes.get(DateTime_.startTime).as(LocalTime.class), startTime, endTime));
        }

        query.where(conditions.toArray(new Predicate[]{}));
        TypedQuery<Academy> academyTypedQuery = entityManager.createQuery(query);

        academyTypedQuery.setParameter(startTime, dto.getTime().generateStartTime());
        academyTypedQuery.setParameter(endTime, dto.getTime().generateEndTime());


        return Optional.ofNullable(academyTypedQuery.getResultList());
    }

}
