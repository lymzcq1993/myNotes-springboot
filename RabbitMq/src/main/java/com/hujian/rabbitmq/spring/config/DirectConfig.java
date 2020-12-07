package com.hujian.rabbitmq.spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hujian
 * @Classname DirectConfig
 * @Description
 * @Date 2020/11/30 23:04
 */
@Configuration
public class DirectConfig {
    @Bean
    Queue springDirectQueue(){
        return new Queue("springDirectQueue",false);
    }

    @Bean
    DirectExchange springDirectExchange(){
        return new DirectExchange("springDirectExchange");
    }

    @Bean
    Binding bindDirect(){
        return BindingBuilder.bind(springDirectQueue()).to(springDirectExchange()).with("springTestKey");
    }


}