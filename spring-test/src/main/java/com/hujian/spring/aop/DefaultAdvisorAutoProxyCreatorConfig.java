package com.hujian.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * @author hujian
 */
//@Configuration
public class DefaultAdvisorAutoProxyCreatorConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ApplicationContext context;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DefaultAdvisorAutoProxyCreatorConfigService service = context.getBean("myDefaultAdvisorAutoProxyCreatorConfigService", DefaultAdvisorAutoProxyCreatorConfigService.class);
        service.test();
        System.out.println("------------------");
        service.test2();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreatorConfigService myDefaultAdvisorAutoProxyCreatorConfigService(){
        return new DefaultAdvisorAutoProxyCreatorConfigService();
    }
    public static class DefaultAdvisorAutoProxyCreatorConfigService{
        public String test(){
            System.out.println("test");
            return this.toString();
        }

        public String test2(){
            System.out.println("test2");
            return this.toString();
        }
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
//        使用方法进行匹配
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        //指定需要被代理的方法
        pointcut.addMethodName("test");

//        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(Component.class,true);
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(pointcut);
        defaultPointcutAdvisor.setAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after-----"+returnValue);
            }
        });

        return defaultPointcutAdvisor;
    }

    /**
     * 这个类必须加载成为bean，因为用于在bean初始化后找到所有需要代理的类进行代理
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

        return defaultAdvisorAutoProxyCreator;
    }

}
