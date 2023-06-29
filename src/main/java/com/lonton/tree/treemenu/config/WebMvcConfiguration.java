package com.lonton.tree.treemenu.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC的配置类
 *
 * @author 张利红
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//访问路径(你要访问我什么东西,我允许你访问)
                .allowedOrigins("http://localhost:8888", "null")//跨域的来源
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")//规定允许跨域的方法
                .allowCredentials(true)//允许是否可以携带信息
                .maxAge(3600);//最大响应时间
    }
}
