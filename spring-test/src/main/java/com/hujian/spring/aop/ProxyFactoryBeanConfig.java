package com.hujian.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * 由Spring提供的代理对象工厂
 * @author hujian
 */
//@Configuration
public class ProxyFactoryBeanConfig implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ApplicationContext context;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ProxyFactoryBeanService service = context.getBean("proxyFactoryBeanService", ProxyFactoryBeanService.class);
        service.test();
    }

    public static class ProxyFactoryBeanService{
        public String test(){
            System.out.println(this);
            return "test";
        }
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBeanService(){
        ProxyFactoryBeanService proxyFactoryBeanService = new ProxyFactoryBeanService();
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(proxyFactoryBeanService);

        //设置环绕增强，需要自己设置执行代理方法的位置
//        proxyFactoryBean.addAdvice(new MethodInterceptor() {
//            @Override
//            public Object invoke(MethodInvocation invocation) throws Throwable {
//                System.out.println("before...");
//                Object result = invocation.proceed();
//                System.out.println("after...");
//                return result;
//            }
//        });
        //通过指定bean的名称来实现增强
        proxyFactoryBean.setInterceptorNames("afterReturningAdvice");
        return proxyFactoryBean;
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
