package com.hujian.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @author hujian
 * @since 2022-05-25 17:40
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.database}")
    private int dbIndex;
    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.minIdle}")
    private int minIdle;
    @Value("${spring.redis.maxActive}")
    private int maxActive;
    @Value("${spring.redis.maxWaitMillis}")
    private Long maxWait;
    @Value("${spring.redis.timeout}")
    private Long timeOut;
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        LettuceConnectionFactory redisConnectionFactory = this.createLettuceConnectionFactory(this.dbIndex, this.hostName, this.port, this.password, this.maxIdle, this.minIdle, this.maxActive, this.maxWait, this.timeOut);
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


    private LettuceConnectionFactory createLettuceConnectionFactory(int dbIndex, String hostName, int port, String password, int maxIdle, int minIdle, int maxActive, Long maxWait, Long timeOut) {
        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(hostName, port);
        ((RedisStandaloneConfiguration)redisConfiguration).setDatabase(dbIndex);
        ((RedisStandaloneConfiguration)redisConfiguration).setPassword(password);
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(timeOut));
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisConfiguration, lettuceClientConfiguration);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }
}
