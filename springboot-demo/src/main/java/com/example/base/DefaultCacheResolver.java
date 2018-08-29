package com.example.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.AbstractCacheResolver;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 时间序列测试
 *
 * @author <a href="mailto:simling82@gmail.com">Simling</a>
 * @version v1.0 on 2017/8/8
 */
@Component("cacheResolver")
@Lazy
public class DefaultCacheResolver extends AbstractCacheResolver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init(){
        super.setCacheManager(SpringContextHolder.getApplicationContext().getBean(CacheManager.class));

    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        for (String key : context.getOperation().getCacheNames()){
            logger.info("key:"+key+" size:"+context.getOperation().getCacheNames().size());
        }
        if(context.getOperation().getCacheNames().contains("UserDao")){
            Set cacheName = new HashSet();
            String className = context.getTarget().getClass().toString();
            String simpleName = context.getTarget().getClass().getSimpleName();
            Method method = context.getMethod();
//            simpleName = simpleName.substring(0, simpleName.length() -11);
            cacheName.add(simpleName);
            logger.info("simpleName:"+simpleName);
            logger.info("className:"+className);
            logger.info("method:"+method.getName());
            logger.info("method:"+(method.getDeclaringClass()));
            ParameterizedType parameterizedType = (ParameterizedType) method.getDeclaringClass().getGenericInterfaces()[0];
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                logger.info("type:"+actualTypeArgument);
            }
//            logger.info("method:"+((ParameterizedType)method.getDeclaringClass().getGenericSuperclass()).getActualTypeArguments());
            return cacheName;
        }else{
            return context.getOperation().getCacheNames();
        }
    }
}
