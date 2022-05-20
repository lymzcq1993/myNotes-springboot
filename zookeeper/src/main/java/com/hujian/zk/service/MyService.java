package com.hujian.zk.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hujian
 * @since 2022-05-20 15:28
 */
@Service
@FeignClient("${spring.application.name}")
public interface MyService {
    /**
     * get请求需要使用@RequestParam修饰，否则会自动转换成post请求
     * @param id
     * @return
     */
    @GetMapping("discovery/get/order")
    String getOrder(@RequestParam String id);
}
