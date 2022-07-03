package com.hujian.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 日志的全局配置，开启Configuration注解会全局生效，测试的时候可以使用
 * @author hujian
 */
//@Configuration
public class FeignClien {
    /**
     * 日志级别
     *        NONE,
     *         BASIC,
     *         HEADERS,
     *         FULL;
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
