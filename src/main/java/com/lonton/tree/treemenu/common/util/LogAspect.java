package com.lonton.tree.treemenu.common.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 操作日志记录处理
 * <p/>
 * @author 张利红
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
     // 设置切入点
    @Pointcut("execution(* com.lonton.tree.treemenu.controller.*.*(..))")
    public void log(){}

     // 在进入controller前进行拦截
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url=request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        String method= joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getClass();
        Object[] args=joinPoint.getArgs();

        content content = new content(url,ip,method,args);
        log.info("Request:{}",content);
    }

    private class content{
        private String url;
        private String ip;
        private String method;
        private Object[] args;

        public content(String url, String ip, String method, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.method = method;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "请求URL='" + url + '\'' +
                    ", 请求ip='" + ip + '\'' +
                    ", 调用方法='" + method + '\'' +
                    ", 传入参数=" + Arrays.toString(args) +
                    '}';
        }
    }
}


