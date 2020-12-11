package com.hujian.rabbitmq.spring.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Bean
    Queue springFanoutQueue(){
        return QueueBuilder.durable(RabbitMqConst.FANOUT_Q_HUJIAN).build();
    }

    @Bean
    Queue springFanoutQueue2(){
        return QueueBuilder.durable(RabbitMqConst.FANOUT_Q_HUJIAN2).build();
    }

    @Bean
    FanoutExchange springFanoutExchange(){
        return new FanoutExchange(RabbitMqConst.FANOUT_EX);
    }

    @Bean
    Binding bindFanout(@Qualifier("springFanoutQueue") Queue queue,@Qualifier("springFanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /*************** topic模式  ****************************/
    @Bean
    Queue springTopicQueue(){
        return QueueBuilder.durable(RabbitMqConst.TOPIC_Q_HUJIAN).build();
    }

    @Bean
    Queue springTopicQueue2(){
        return QueueBuilder.durable(RabbitMqConst.TOPIC_Q_HUJIAN2).build();
    }

    @Bean
    TopicExchange springTopicExchange(){
        return new TopicExchange(RabbitMqConst.TOPIC_EX);
    }

    @Bean
    Binding bindHujian(@Qualifier("springTopicQueue") Queue queue,@Qualifier("springTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConst.TOPIC_KEY_HUJIAN);
    }

    @Bean
    Binding bindHujian2(@Qualifier("springTopicQueue2") Queue queue,@Qualifier("springTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConst.TOPIC_KEY_HUJIAN2);
    }

}