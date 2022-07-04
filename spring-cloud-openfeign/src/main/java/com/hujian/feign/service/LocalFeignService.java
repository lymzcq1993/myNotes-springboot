package com.hujian.feign.service;

import com.hujian.feign.interceptor.MyFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hujian
 * 本地模式调用feign，需要指定url，value是自己随便定义的服务名称
 */
@FeignClient(value = "localService",url = "127.0.0.1:8080"
        ,configuration = MyFeignInterceptor.class
        )
public interface LocalFeignService {
    @GetMapping("/feign/test")
    String test(@RequestParam String name);
}
