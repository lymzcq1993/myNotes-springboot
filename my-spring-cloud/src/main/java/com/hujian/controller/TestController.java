package com.hujian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hujian
 * @description
 * @date 2021/1/6 16:19
 */
@RestController
@RequestMapping("/order")
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getServerList")
    public List<ServiceInstance> getServerList(){
        List<ServiceInstance> instances = discoveryClient.getInstances("order-center");
        return instances;
    }

    @GetMapping("/getProductList")
    public List<ServiceInstance> getProductList(){
        List<ServiceInstance> instances = discoveryClient.getInstances("product-center");
        return instances;
    }
}
