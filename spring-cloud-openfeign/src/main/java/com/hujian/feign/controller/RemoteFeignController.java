package com.hujian.feign.controller;

import com.hujian.feign.entity.NacosOrder;
import com.hujian.feign.service.RemoteFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hujian
 */
@Api(tags = "测试feifn")
@RestController
@RequestMapping("/feign")
public class RemoteFeignController {
    @Autowired
    private RemoteFeignService remoteFeignService;

    @GetMapping("/getProductList")
    @ApiOperation(value = "获取所有实例名")
    public List<Object> getServerList(){
        return remoteFeignService.getServerList();
    }


    @PostMapping("/getNacosOrder")
    @ApiOperation(value = "获取nacos order")
    public NacosOrder getNacosOrder(@RequestBody  NacosOrder nacosOrder){
        return remoteFeignService.getNacosOrder(nacosOrder);
    }

}
