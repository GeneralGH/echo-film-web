package com.yiyu.echofilmspringboot.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建API应用
     * apiInfo()增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，控制哪些接口暴露给Swagger展示
     * @return
     */

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("标准接口")
                .apiInfo(apiInfo("回声影视", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yiyu.echofilmspringboot"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://ip:port/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("回声影视前后端项目")
                .version(version)
                .build();
    }
}
