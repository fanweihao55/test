package com.yichuangzhihui.robotvrp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.customInterceptor());
    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(60*60*24)
//                .allowedHeaders("*")
//                .allowCredentials(true);//设置成允许操作cookie
//    }

//    @Bean
//    public CacheInterceptor cacheInterceptor(){
//        return new CacheInterceptor();
//    }


    @Bean
    public CustomInterceptor customInterceptor(){
        return new CustomInterceptor();
    }
}
