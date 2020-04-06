package com.cnblogs.kmpp.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket api() {
        System.out.println("04/05/2020 下午18:58 Line:22,当前类=SwaggerConfig.api()");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                /*
                 * 重要的两个方法:
                 *                  apis():指定要生成文档的接口包基本路径
                 *                  paths():指定针对哪些请求生成接口文档
                 */
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.cnblogs.kmpp.api"))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringMVC Multi Module Project Demo")
                .description("源码主要用于学习整合SSM框架：SpringMVC + Spring + MyBatis 构建maven多模块工程项目，根据不同功能划分模块，方便管理和解耦。项目主要涉及到的内容有：基于SpringMVC框架（非SpringBoot启动方式），结合MyBatis构建数据库访问层，以及使用AOP实现方法切面拦截，以及结合Redis高度封装类RedisTemplate实现数据缓存增、删、改、查操作。")
                .version("1.0.0")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .build();
    }
}
