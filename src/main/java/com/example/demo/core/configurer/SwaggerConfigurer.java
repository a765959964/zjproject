package com.example.demo.core.configurer;

import com.example.demo.core.constant.ProjectConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zf
 * @Description:Swagger2 配置文件
 * @time 2018/4/20 22:42
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurer {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(ProjectConstant.CONTROLLER_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mySpringBoot 使用Swagger2构建RESTful APIs")
                .description("")
                .termsOfServiceUrl("https://juejin.im/user/59e7fb9451882578e1406a51/posts")
                .contact(new Contact("zf","", null))
                .version("1.0")
                .build();
    }
}
