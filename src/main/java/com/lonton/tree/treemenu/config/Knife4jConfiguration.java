package com.lonton.tree.treemenu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 接口文档配置 <p>
 * https://doc.xiaominfo.com/knife4j/documentation/get_start.html
 * <ol>
 *    <li>接口的文档在线自动生成
 *    <li>前后端分离，功能测试
 * </ol>
 *
 * @author 张利红
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class Knife4jConfiguration {

    @Bean
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("project-backend")
                        .description("project-backend")
                        .version("1.0")
                        .build())
                .select()
                // 指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lonton.tree.treemenu.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}