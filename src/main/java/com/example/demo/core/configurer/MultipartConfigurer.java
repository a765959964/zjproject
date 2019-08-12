package com.example.demo.core.configurer;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 设置文件上传大小
 */
@Configuration
public class MultipartConfigurer {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //设置单文件上传大小
        factory.setMaxFileSize("10MB");
        //设置总上传文件数据大小
        factory.setMaxRequestSize("10MB");
        return factory.createMultipartConfig();
    }
}
