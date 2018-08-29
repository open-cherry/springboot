package com.example.base;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 时间序列测试
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/8
 */
@NoRepositoryBean
@CacheConfig(cacheNames = "UserDao", cacheResolver = "defaultCacheResolver")
public interface BaseDao<T,ID> extends JpaRepository<T, ID> {

    @CachePut(key = "#p0.id")
    <S extends T> S save(S entity);

    @Cacheable(key = "#p0")
    T getOne(Long id);
}
