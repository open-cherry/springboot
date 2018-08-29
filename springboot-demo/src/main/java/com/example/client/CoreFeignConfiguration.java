/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CoreFeignConfiguration
 * Author:   chengaochang
 * Date:     18/8/29 上午11:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.client;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author cgc
 * @create 18/8/29
 * @since 1.0.0
 */
@Configuration
public class CoreFeignConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    // new一个form编码器，实现支持form表单提交
    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    // 开启Feign的日志
    @Bean
    public Logger.Level logger() {
        return Logger.Level.FULL;
    }

}