package com.hujian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hujian
 * @description
 * @date 2021/1/6 16:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringStart.class,args);
    }
}
