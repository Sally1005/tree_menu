package com.lonton.tree.treemenu.config;

import com.lonton.tree.treemenu.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 授权认证
 *
 * @author 张利红
 */
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         *白名单
         */

        String[] urls={"/doc.html",
        "/**/*.js",
        "/**/*.css",
        "/v2/api-docs/**",
        "/favicon.ico",
        "/users/login",
        "/sys/user/treeMenu"};

        http.cors(); // 允许跨域访问

        http.csrf().disable();// 禁用 防止伪造跨域攻击（列：夹杂在图片标签中）
        http.authorizeRequests() // 要求请求必须被授权
                .antMatchers(urls)  // 匹配一些路径
                .permitAll() // 允许访问
                .anyRequest() // 除以上配置以外的请求
                .authenticated(); // 经过认证的
        // http.formLogin();//表单登录：白名单以外的路径，都会重定向到登录页面
        // 将JWT过滤器添加在认证过滤器之前
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
