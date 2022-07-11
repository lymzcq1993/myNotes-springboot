package com.hujian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hujian
 * @date 2021/1/6 16:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayStart {


    public static void main(String[] args) {
        SpringApplication.run(GatewayStart.class,args);
    }
}
