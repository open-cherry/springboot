package com.example.controller;


import com.example.client.UserClient;
import com.example.entity.User;
import com.example.proto.URI;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/3
 */
@RestController
public class UserHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserService userService;

    @RequestMapping(URI.SMCU_USER_ADD)
    public void add(@Valid User user, BindingResult bindingResult){
        logger.info("add:"+user);
        userService.add(user);
    }

    @RequestMapping(name = URI.SMCU_USER_UPDATE)
    public User update(User user){
        logger.info("update:"+user);
        Assert.notNull(user.getId(), "Assert is null");
        return userService.update(user);
    }

    @RequestMapping(URI.SMCU_USER_GET)
    public User get(Long id){
//        logger.info("get:"+id);
        Assert.notNull(id, "Assert is null");
        return userService.get(id);
    }

    @RequestMapping(URI.SMCU_USER_QUERY)
    public Page<User> query(@PageableDefault Pageable pageRequest, String username){
        logger.info("query:"+pageRequest + " username:"+username);
        if(StringUtils.isEmpty(username) == true){
            return userService.findAll(pageRequest);
        } else {
            List<User> ls = userService.queryUserByName(username);
            Page<User> page = new PageImpl(ls);
            return page;
        }
    }

    @RequestMapping("/smcu/user/getByProxy")
    public User getByProxy(Long id){
//        logger.info("get:"+id);
        Assert.notNull(id, "Assert is null");
        return userClient.getUser(id);
    }

    @RequestMapping("/smcu/user/updateByProxy")
    public User updateByProxy(User user) throws Exception {
        logger.info("update:"+user);
        Assert.notNull(user.getId(), "Assert is null");
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(mapper.writeValueAsString(user), Map.class);
        return userClient.update2(map);
    }

//    @RequestMapping(name = URI.SMCU_USER_UPDATE_BY_JSON,method = RequestMethod.POST)
//    public User updateByJson(@RequestBody User user){
//        logger.info("update:"+user);
//        Assert.notNull(user.getId(), "Assert is null");
//        return userService.update(user);
//    }
}
