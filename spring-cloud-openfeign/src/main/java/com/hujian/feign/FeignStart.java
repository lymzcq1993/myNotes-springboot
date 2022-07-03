package com.hujian.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author hujian
 * @date 2022/7/3 16:15
 */
@SpringBootApplication
@EnableFeignClients
public class FeignStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FeignStart.class, args);
//        LocalFeignService myFeignService = context.getBean(LocalFeignService.class);
//        myFeignService.test("hujian");

    }
}
