package com.hujian.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author hujian
 * @since 2022-07-04 15:55
 */
public class MyFeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //通过配置和拦截器来指定header
        requestTemplate.header("huijian","ceshi");
//        requestTemplate.body("bbb");
    }
}
