package com.hujian.feign;

import feign.Client;
import feign.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author hujian
 * @date 2022/7/3 16:15
 */
@SpringBootApplication
@EnableFeignClients
public class FeignStart {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FeignStart.class, args);
        Client client = context.getBean(Client.class);
        System.out.println(client);
//        LocalFeignService myFeignService = context.getBean(LocalFeignService.class);
//        myFeignService.test("hujian");

    }
    @Bean
    public Request.Options options(){
        //参数为connectTimeout，readTimeout，是否重定向
        //默认值为10s，60s，true
        return new Request.Options(5, TimeUnit.SECONDS,10,TimeUnit.SECONDS,true);
    }

//    /**
//     * 全局配置feign的拦截器
//     * @return
//     */
//    @Bean
//    MyFeignInterceptor myFeignInterceptor(){
//        return new MyFeignInterceptor();
//    }


//    /**
//     * 日志级别
//     *        NONE,
//     *         BASIC,
//     *         HEADERS,
//     *         FULL;
//     */
//    @Bean
//    public Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
}
