package com.hujian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author hujian
 * @description
 * @date 2021/1/6 16:15
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringStart {
    /**
     * 负载的关键注解LoadBalanced，引入spring-cloud-starter-alibaba-nacos-discovery的时候已经带了spring-cloud-commons
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringStart.class,args);
    }
}
