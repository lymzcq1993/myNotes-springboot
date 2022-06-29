package com.hujian.spring.aop;

import com.hujian.spring.service.UserService;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用Spring提供的cglib实现动态代理
 * @author hujian
 */
public class CglibAOP {
    public static class CglibService {
        public void test(){
            System.out.println(this);
        }
    }


    public static void main(String[] args) {
        CglibService target= new CglibService();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallbacks(new Callback[]{
                new MethodInterceptor() {
                    @Override
                    //o是代理的对象
                    //method是执行目标方法，可以不用依赖外部参数
                    //objects 是参数列表
                    //methodProxy 代理执行方法
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        System.out.println("before");
                        //使用cglib代理对象执行
                        Object result = methodProxy.invoke(target, objects);
//                        Object result = method.invoke(target, objects);
                        //错误用法，这里用代理方法去执行代理对象就会导致循环执行最终StackOverflowError
//                        Object result = methodProxy.invoke(o, objects);
                        System.out.println("after");
                        return result;
                    }
                }
        });

        CglibService service = (CglibService) enhancer.create();
        service.test();
    }
}
