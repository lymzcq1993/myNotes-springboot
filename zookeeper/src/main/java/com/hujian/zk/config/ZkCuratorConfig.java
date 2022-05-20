package com.hujian.zk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author hujian
 * @since 2022-05-20 11:37
 */
@Slf4j
@Configuration
public class ZkCuratorConfig {
//    private final String CONNECT_STR = "127.0.0.1:2181";
//    @Bean
//    public CuratorFramework init(){
//        CuratorFramework client = CuratorFrameworkFactory.builder()
//                .connectString(CONNECT_STR)
//                //连接超时时间
//                .sessionTimeoutMs(5000)
//                //重试策略
//                .retryPolicy(new ExponentialBackoffRetry(5000, 5))
//                .namespace("hujian")
//                .build();
//        client.start();
//        return client;
//    }
}
