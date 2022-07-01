package com.hujian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author hujian
 * @description
 * @date 2021/1/6 16:19
 */
@RestController
@RequestMapping("/order")
@RefreshScope
public class TestController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取配置中心的属性
     */
    @Value("${my.hobby}")
    private String hobby;

    @RequestMapping("getOrderById")
    public String getOrderById(){
        String url = "http://product-center/order/getProductList";
        return restTemplate.getForObject(url,String.class);
    }


    @GetMapping("/getServerList")
    public List<ServiceInstance> getServerList(){
        List<ServiceInstance> instances = discoveryClient.getInstances("order-center");
        return instances;
    }

    @GetMapping("/getConfig")
    public String getConfig(){
        return hobby;
    }

    @GetMapping("/getProductList")
    public List<ServiceInstance> getProductList(){
        System.out.println(888888);
        List<ServiceInstance> instances = discoveryClient.getInstances("product-center");
        return instances;
    }
}
