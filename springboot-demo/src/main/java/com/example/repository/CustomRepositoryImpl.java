package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by cgc on 18/1/4.
 */
@NoRepositoryBean
class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {

    private EntityManager em;

    private JpaEntityInformation<T, ?> entityInformation;


    public CustomRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
    }

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    @Override
    public List<T> findAll(Specification<T> spec, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(spec, Sort.unsorted());
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.getResultList();
    }

    @Override
    public List<T> findAll(EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(null, Sort.unsorted());
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.getResultList();
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(spec, pageable);
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return pageable == null ? new PageImpl<T>(query.getResultList())
                : readPage(query, getDomainClass(), pageable, spec);
    }

    @Override
    public Page<T> findAll(Pageable pageable, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        return findAll(null,pageable,entityGraphType,entityGraphName);
    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(spec, sort);
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.getResultList();
    }

    @Override
    public List<T> findAll(Sort sort, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(null, sort);
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.getResultList();
    }

    @Override
    public T findOne(Specification<T> spec, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {
        TypedQuery<T> query = getQuery(spec, Sort.unsorted());
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.getSingleResult();
    }

    public List<T> findAllById(Iterable<ID> ids, EntityGraph.EntityGraphType entityGraphType, String entityGraphName) {

        Assert.notNull(ids, "The given Iterable of Id's must not be null!");

        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (entityInformation.hasCompositeId()) {

            List<T> results = new ArrayList<T>();

            for (ID id : ids) {
                findById(id).ifPresent(results::add);
            }

            return results;
        }

        ByIdsSpecification<T> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<T> query = getQuery(specification, Sort.unsorted());
        query.setHint(entityGraphType.getKey(), em.getEntityGraph(entityGraphName));
        return query.setParameter(specification.parameter, ids).getResultList();
    }

    public T findOne(ID id) {
        return findById(id).get();
    }

    @SuppressWarnings("rawtypes")
    private static final class ByIdsSpecification<T> implements Specification<T> {

        private final JpaEntityInformation<T, ?> entityInformation;

        @Nullable
        ParameterExpression<Iterable> parameter;

        ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
         */
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            Path<?> path = root.get(entityInformation.getIdAttribute());
            parameter = cb.parameter(Iterable.class);
            return path.in(parameter);
        }
    }
}

