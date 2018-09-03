/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: UserClient
 * Author:   chengaochang
 * Date:     18/8/29 上午11:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.client;

import com.example.entity.User;
import com.example.proto.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cgc
 * @create 18/8/29
 * @since 1.0.0
 */
@FeignClient(name = "users",url = "localhost:8080",configuration = CoreFeignConfiguration.class)
public interface UserClient {
    @GetMapping(value = "/smcu/user/get?id={id}")
    User getUser(@PathVariable("id") Long id);

    @PostMapping(value = URI.SMCU_USER_UPDATE_BY_JSON)
    User update(User user);

    @PostMapping(value = URI.SMCU_USER_UPDATE,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    User update2(Map<String,?> user2);
}