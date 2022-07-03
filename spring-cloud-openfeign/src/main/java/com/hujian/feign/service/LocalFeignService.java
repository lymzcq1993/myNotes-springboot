package com.hujian.feign.service;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hujian
 * 本地模式调用feign，需要指定url，value是自己随便定义的服务名称
 */
@FeignClient(value = "localService",url = "127.0.0.1:8080",configuration = LocalFeignService.FeignConfig.class)
public interface LocalFeignService {
    @GetMapping("/feign/test")
    String test(@RequestParam String name);

    class FeignConfig implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate requestTemplate) {
            //通过配置和拦截器来指定header
            requestTemplate.header("huijian","ceshi");
        }
    }
}
