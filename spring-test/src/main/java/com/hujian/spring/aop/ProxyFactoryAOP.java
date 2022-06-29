package com.hujian.spring.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 由Spring提供的代理对象工厂
 * @author hujian
 */
public class ProxyFactoryAOP {
    public static class ProxyFactoryService implements ProxyFactoryInterface{
        @Override
        public String test(){
            System.out.println(this);
            return "test";
        }
    }

    public static interface ProxyFactoryInterface{
        public String test();
    }

    public static void main(String[] args) {
        ProxyFactoryService proxyFactoryService = new ProxyFactoryService();
        ProxyFactoryInterface proxyFactoryInterface = new ProxyFactoryService();
        ProxyFactory proxyFactory = new ProxyFactory();
        //设置代理的对象
        proxyFactory.setTarget(proxyFactoryInterface);
        proxyFactory.setInterfaces(ProxyFactoryInterface.class);
//        proxyFactory.setInterfaces(proxyFactoryInterface.class);
//        前置增强。
//        proxyFactory.addAdvice(new MethodInterceptor() {
//            @Override
//            public Object invoke(MethodInvocation invocation) throws Throwable {
//                return null;
//            }
//        });
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after");
            }
        });
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("before...");
            }
        });
        //后置增强
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("after returning..."+returnValue);
            }
        });
        ProxyFactoryInterface proxy = (ProxyFactoryInterface) proxyFactory.getProxy();
        proxy.test();
    }
}
