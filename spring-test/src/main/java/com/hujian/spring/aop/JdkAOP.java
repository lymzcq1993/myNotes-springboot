package com.hujian.spring.aop;

import com.hujian.spring.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author hujian
 */
public class JdkAOP {
    public static interface JdkService {
        void test();
    }

    public static class JdkServiceImpl implements JdkService {
        @Override
        public void test(){
            System.out.println(this);
        }
    }

    public static void main(String[] args) {
        JdkService target = new JdkServiceImpl();

        // JdkService接口的代理对象,被代理的对象必须要有接口
        Object proxy = Proxy.newProxyInstance(JdkService.class.getClassLoader(), new Class[]{JdkService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before...");
                Object result = method.invoke(target, args);
                System.out.println("after...");
                return result;
            }
        });

        JdkService userService = (JdkService) proxy;
        userService.test();
    }
}
