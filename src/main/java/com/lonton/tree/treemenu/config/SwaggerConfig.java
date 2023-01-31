package com.lonton.tree.treemenu.config;

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
 * Swagger配置类 <p/>
 * 1. 接口的文档在线自动生成<p/>
 * 2. 前后端分离，功能测试<p/>
 * @author 张利红
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket buildDocket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(buildApiInfo())
                    .select()
                    // 要扫描的API(Controller)基础包
                    .apis(RequestHandlerSelectors.basePackage("com.lonton.tree.treemenu"))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo buildApiInfo() {
            Contact contact = new Contact("Sally","","");
            return new ApiInfoBuilder()
                    .title("权限管理API文档")
                    .description("后台api")
                    .contact(contact)
                    .version("1.0.0").build();
        }

}
