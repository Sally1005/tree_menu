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
 * AOP采取横向抽取机制，取代传统纵向继承体系
 * 经典应用：事务管理、性能测试（简单：时间差）、缓存、日志等
 * @author 张利红
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * AOP术语
     * 1.target:目标类，需要被代理的类；
     * 2.Jointpoint(连接点)：指那些可能被拦截到的方法
     * 3.Pointcut:切入点，已经被拦截的方法，2的子集
     * 4.advice：通知/增强，增强代码，eg:after、before
     * 5.Weaving:把增强advice应用到目标对象target来创建新的代理对象proxy的过程
     * 6.Aspect:切面，通知advice和切入点Pointcut的结合
     *
     */
     // 设置切入点
    @Pointcut("execution(* com.lonton.tree.treemenu.controller.*.*(..))")
    public void log(){}

     // 在进入controller前进行拦截 通知（Advice):在切面的某个特定的切入点上执行的操作
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


