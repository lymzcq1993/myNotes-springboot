package com.hujian.feign.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 */
@Api(tags = "测试feifn")
@RestController
@RequestMapping("/feign")
public class TestFeignController {
    @GetMapping("/test")
    @ApiOperation(value = "getName")
    public String getName(String name,@RequestHeader HttpHeaders headers){
        return name;
    }

}
