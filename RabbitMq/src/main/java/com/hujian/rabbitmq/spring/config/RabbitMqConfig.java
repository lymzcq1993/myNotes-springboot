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
public class RabbitMqConfig {
    /*************** direct模式  ****************************/
    @Bean
    Queue springDirectQueue(){
        return new Queue(RabbitMqConst.DIRECT_Q_HUJIAN,false);
    }

    @Bean
    DirectExchange springDirectExchange(){
        return new DirectExchange(RabbitMqConst.DIRECT_EX);
    }

    @Bean
    Binding bindDirect(){
        return BindingBuilder.bind(springDirectQueue()).to(springDirectExchange()).with(RabbitMqConst.DIRECT_KEY);
    }
    /*************** fanout模式  ****************************/

}