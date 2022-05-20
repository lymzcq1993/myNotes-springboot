package com.hujian.zk.controller;

import cn.hutool.core.util.RandomUtil;
import com.hujian.zk.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 * @since 2022-05-20 14:51
 */
@RestController
@RequestMapping("/discovery")
public class ZkDiscoveryController {
    @Autowired
    private MyService myService;
    @GetMapping("/test")
    public String testFeign(String id){
        return myService.getOrder(id);
    }


    @GetMapping("/get/order")
    public String getOrder(@RequestParam String id){
        String s = id + RandomUtil.randomString(3);
        System.out.println(s);
        return s;
    }
}
