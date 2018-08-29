package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 时间序列测试
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/3
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserDao userDao;

    public User add(User user){
        logger.info("add:"+user);
        return userDao.save(user);
    }

    public User update(User user){
        logger.info("update:"+user);
        return userDao.save(user);
    }

    public User get(Long id){
        User user = userDao.findOne(id);
        return user;
    }

    public Page<User> findAll(Pageable pageable){
        logger.info("findAll:"+pageable);
        return userDao.findAll(pageable);
    }

    public List<User> queryUserByName(String name){
        logger.info("queryUserByName:"+name);
        return userDao.queryUserByName(name);
    }
}
