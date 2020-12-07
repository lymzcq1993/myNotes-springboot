package com.hujian.redis;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


/**
 * @author hujian
 * @Classname RedissonConfig
 * @Description
 * @Date 2020/11/14 10:10
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redisson(@Value("classpath:redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());  
        return Redisson.create(config);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String m = DigestAuthenticationProvider.generateDigest("super:hujian");
        System.out.println(m);
    }
}