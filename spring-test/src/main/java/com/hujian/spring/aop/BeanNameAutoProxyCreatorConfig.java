package com.hujian.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 *  通过指定bean的名字来批量匹配
 * @author hujian
 */
//@Configuration
public class BeanNameAutoProxyCreatorConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ApplicationContext context;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        BeanNameAutoProxyCreatorService service = context.getBean("myBeanNameAutoProxyCreatorService", BeanNameAutoProxyCreatorService.class);
        service.test();
    }

    @Bean
    public BeanNameAutoProxyCreatorService myBeanNameAutoProxyCreatorService(){
        return new BeanNameAutoProxyCreatorService();
    }
    public static class BeanNameAutoProxyCreatorService{
        public String test(){
            System.out.println(this);
            return this.toString();
        }
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("myBeanName*");
        beanNameAutoProxyCreator.setInterceptorNames("afterReturningAdvice");
        beanNameAutoProxyCreator.setProxyTargetClass(true);

        return beanNameAutoProxyCreator;
    }

    @Bean
    AfterReturningAdvice afterReturningAdvice(){
        return new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after-----"+returnValue);
            }
        };
    }
}
