package com.hujian.spring.aop;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 通过EnableAspectJAutoProxy注解引入解析切面的配置类，也可以通过@Import引入
 * @author hujian
 */
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AspectAOP implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        AspectService bean = applicationContext.getBean(AspectService.class);
        bean.test();
    }

    @Bean
    AspectService aspectService(){
        return new AspectService();
    }

    @Data
    public static class AspectService{
        public void test(){
            System.out.println(this);
        }
    }
    @Pointcut("execution(public void com.hujian.spring.aop.AspectAOP.AspectService.test())")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before----");
        pjp.proceed();
        System.out.println("after----");

    }
}
