package com.lonton.tree.treemenu.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * TransactionConfig<p/>
 * 事务管理配置，拦截controller包下方法
 *
 * @author 张利红
 */
@Aspect
@Configuration
public class TransactionConfig {

    /**
     * 切面地址
     */
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.lonton.tree.treemenu.controller.*.*(..))";
    /**
     * 事务失效时间
     */
    private static final int TX_METHOD_TIMEOUT = 5;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 事务配置
     */
    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        /**
         * PROPAGATION_REQUIRED:事务隔离性为 1
         * 若当前存在事务，则加入该事务
         * 如果当前没有事务，则创建一个新的事务，这是默认值
         */
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /**
         * 设置事务失效时间，如果超过5秒，则回滚事务
         */
        txAttr_REQUIRED.setTimeout(TX_METHOD_TIMEOUT);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        /**
         * transaction definition 定义事务的隔离级别；
         * PROPAGATION_NOT_SUPPORTED 事务传播级别5，以非事务运行，如果当前存在事务，则把当前事务挂起
         */
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        /**
         * 设置当前事务是否为只读事务
         * true为只读
         */
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        /**
         * 事务管理规则，声明具备事务管理的方法名
         */
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        source.addTransactionalMethod("search*", txAttr_REQUIRED);

        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);

        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 利用AspectJExpressionPointcut设置切面=切点+通知（写成内部bean的方式）
     *
     * @return 切面通知实现类
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        /**
         * 声明切点的面
         * 切面（Aspect）：切面就是通知和切入点的结合。
         * 通知和切入点共同定义了关于切面的全部内容——它的功能、在何时和何地完成其功能
         */
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        /**
         * 声明和设置需要拦截的方法,用切点语言描写
         */
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        /**
         * 设置切面=切点pointcut+通知TxAdvice
         */
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
