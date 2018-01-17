package com.educhoice.motherchoice.models.persistent.repositories.specifications.implementations;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.repositories.specifications.AcademyRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class AcademyRepositoryImpl implements AcademyRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Academy> findAcademyIdCriteria(long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Academy> query = cb.createQuery(Academy.class);
        Root<Academy> c = query.from(Academy.class);

        query.select(c).where(cb.equal(c.get("academyId"), id));

        TypedQuery<Academy> resultQuery = entityManager.createQuery(query);

        return Optional.ofNullable(resultQuery.getSingleResult());
    }
}
