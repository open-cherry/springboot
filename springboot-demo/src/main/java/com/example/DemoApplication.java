package com.example;

import com.example.base.SpringContextHolder;
import com.example.controller.databind.DynamicFilterMixIn;
import com.example.controller.databind.DynamicFilterProvider;
import com.example.controller.databind.DynamicFilterResponseBodyAdvice;
import com.example.repository.CustomRepositoryFactoryBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
@EnableFeignClients
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		SpringContextHolder.setApplicationContext(context);
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {

		return getJacksonBuilder();
	}

	@Bean
	public DynamicFilterResponseBodyAdvice dynamicFilterResponseBodyAdvice() {
		return getDynamicFilterResponseBodyAdvice();
	}

	protected Jackson2ObjectMapperBuilder getJacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
				.serializationInclusion(JsonInclude.Include.NON_NULL)
				.mixIn(Object.class, DynamicFilterMixIn.class)
				.filters(new DynamicFilterProvider());
		return b;
	}


	protected DynamicFilterResponseBodyAdvice getDynamicFilterResponseBodyAdvice() {
		return new DynamicFilterResponseBodyAdvice();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*")
//                        .exposedHeaders("access-control-allow-headers",
//                                "access-control-allow-methods",
//                                "access-control-allow-origin",
//                                "access-control-max-age",
//                                "X-Frame-Options")
						.allowCredentials(false).maxAge(3600);
			}
		};

	}
}
