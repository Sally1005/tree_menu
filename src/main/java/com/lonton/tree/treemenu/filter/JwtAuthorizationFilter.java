package com.lonton.tree.treemenu.filter;


import com.alibaba.fastjson.JSON;
import com.lonton.tree.treemenu.security.LoginPrincipal;
import com.lonton.tree.treemenu.web.JsonResult;
import com.lonton.tree.treemenu.web.ServiceCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * JWT认证过滤器</p>
 *
 * Spring Security框架会自动从SecurityContext读取认证信息，如果存在有效信息，则视为已登录，否则，视为未登录</p>
 * 当前过滤器应该尝试解析客户端可能携带的JWT，如果解析成功，则创建对应的认证信息，并存储到SecurityContext中。
 *
 * @author 张利红
 */
@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Value("${treemenu.jwt.secret-key}")
    private String secretKey;


    public JwtAuthorizationFilter() {
        log.debug("创建过滤器：JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.debug("执行JwtAuthorizationFilter");
        //清除Security上下文中的数据
        SecurityContextHolder.clearContext();

        //从请求头中获取JWT
        String jwt = request.getHeader("Authorization");
        //
        log.debug("从请求头中获取jwt:{}", jwt);

        //判断是否携带JWT
        if (!StringUtils.hasText(jwt) || jwt.length() < 80) {
            log.debug("获取到的JWT是无效的，直接放行，交由后续的组件执行");
            // 过滤器链继续执行，相当于：放行
            filterChain.doFilter(request, response);
            // 返回，终止当前方法本次执行
            return;
        }

        // 设置响应结果的文档类型，主要用于处理解析JWT时的异常
        response.setContentType("application/json; charset=utf-8");

        // 尝试解析JWT
        log.debug("开始生成secretKey:{}", secretKey);
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (MalformedJwtException e) {
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_PARSE, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        } catch (SignatureException e) {
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_PARSE, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        } catch (ExpiredJwtException e) {
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_EXPIRED, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        } catch (Throwable e) {
            log.warn("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            JsonResult<Void> jsonResult = JsonResult.fail(
                    ServiceCode.ERR_JWT_PARSE, "无法获取到有效的登录信息，请重新登录！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonResultString);
            writer.close();
            return;
        }

        // 从JWT的解析结果中获取数据
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        String authorityListString = claims.get("authorities", String.class);
        log.debug("从JWT中解析得到username：{}", username);
        log.debug("从JWT中解析得到authorities：{}", authorityListString);

        // 准备Authentication对象，后续会将此对象封装到Security的上下文中
        LoginPrincipal loginPrincipal = new LoginPrincipal();
        loginPrincipal.setId(id);
        loginPrincipal.setUserName(username);
        List<SimpleGrantedAuthority> authorities = JSON.parseArray(
                authorityListString, SimpleGrantedAuthority.class);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginPrincipal, null, authorities);

        //将用户信息封装到Security的上下文中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        // 过滤器链继续执行，相当于：放行
        filterChain.doFilter(request, response);
        //eyJhbGciOiJIUzI1NiIsInR5cCI6Imp3dCJ9.eyJuYW1lIjoiYmlncmVkIiwiaWQiOjk1MjcsImV4cCI6MTY2MTE2MDI5NX0.vIPsM3GGUnyBwV51-tntT0HlezuAsD7B_I5wfvvyuf0

    }
}
