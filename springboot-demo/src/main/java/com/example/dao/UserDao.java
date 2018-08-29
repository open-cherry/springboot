package com.example.dao;


import com.example.entity.User;
import com.example.repository.CustomRepository;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


/**
 * 基础接口继承CRUD和Page
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/3
 */
@CacheConfig(cacheNames = "UserDao")
public interface UserDao extends CustomRepository<User, Long> {

    @CachePut(key = "#p0.id")
    @Caching(evict={@CacheEvict(key="#p0.username"), @CacheEvict(key="'findAll'")})
    User save(User user);

    @Cacheable(key = "#p0")
    User findOne(Long id);

    @Cacheable(key = "'queryUserByName'")
    @Query("select u from User u where u.username like %?1%")
    List<User> queryUserByName(String name);

    @Cacheable(key = "'findAll'")
    Page<User> findAll(Pageable pageable);

}
