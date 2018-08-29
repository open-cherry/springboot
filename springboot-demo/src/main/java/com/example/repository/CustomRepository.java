package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cgc on 18/1/4.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    List<T> findAll(Specification<T> spec, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    List<T> findAll(EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    List<T> findAll(Specification<T> spec, Sort sort, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    List<T> findAll(Sort sort, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    Page<T> findAll(Specification<T> spec, Pageable pageable, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    Page<T> findAll(Pageable pageable, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    T findOne(Specification<T> spec, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    List<T> findAllById(Iterable<ID> ids, EntityGraph.EntityGraphType entityGraphType, String entityGraphName);
    T findOne(ID id);

}
