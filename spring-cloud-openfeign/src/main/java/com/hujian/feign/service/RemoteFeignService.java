package com.hujian.feign.service;

import com.hujian.feign.entity.NacosOrder;
import com.hujian.feign.interceptor.MyFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author hujian
 * 使用注册中心
 */
@FeignClient(value = "product-center",path ="order" ,configuration = MyFeignInterceptor.class)
public interface RemoteFeignService {
    /**
     * 获取所有服务列表
     * @return
     */
    @GetMapping("getProductList")
//    @Headers({"Content-Type: application/json","Accept: application/json"})
    List<Object> getServerList();

    @PostMapping("getBody")
    NacosOrder getNacosOrder(@RequestBody NacosOrder nacosOrder);

}
