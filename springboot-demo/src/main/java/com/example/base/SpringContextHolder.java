package com.example.base;

import org.springframework.context.ApplicationContext;

/**
 * 时间序列测试
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/8
 */
public class SpringContextHolder {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static Object getBean(Class requiredType){
        return applicationContext.getBean(requiredType);
    }

}
