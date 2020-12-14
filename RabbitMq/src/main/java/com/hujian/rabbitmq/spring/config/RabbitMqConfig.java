package com.hujian.rabbitmq.spring.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
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
@EnableRabbit
public class RabbitMqConfig {
    /*************** direct模式  ****************************/
    @Bean
    Queue springDirectQueue(){
        return new Queue(RabbitMqConst.DIRECT_Q_HUJIAN,true);
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

    /**
     * 这里设置了一个ttl队列
     * @return
     */
    @Bean
    Queue springFanoutTTLQueue(){
        return QueueBuilder.durable(RabbitMqConst.FANOUT_Q_TTL).ttl(5000).build();
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
        Binding binder = BindingBuilder.bind(queue).to(exchange);
        return binder;
    }

    @Bean
    Binding bindFanoutTTL(@Qualifier("springFanoutTTLQueue") Queue queue,@Qualifier("springFanoutExchange") FanoutExchange exchange){
        Binding binder = BindingBuilder.bind(queue).to(exchange);
        return binder;
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
        return ExchangeBuilder.topicExchange(RabbitMqConst.TOPIC_EX).durable(true).build();
    }

    @Bean
    Binding bindHujian(@Qualifier("springTopicQueue") Queue queue,@Qualifier("springTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConst.TOPIC_KEY_HUJIAN);
    }

    @Bean
    Binding bindHujian2(@Qualifier("springTopicQueue2") Queue queue,@Qualifier("springTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitMqConst.TOPIC_KEY_HUJIAN2);
    }

    /**********************测试死信  *************************/
    @Bean
    Queue springDLXNormalTopicQueue(){
        return QueueBuilder.durable(RabbitMqConst.DLX_Q_NORMAL_HUJIAN)
                .deadLetterExchange(RabbitMqConst.DLX_EX)
                .deadLetterRoutingKey("dlx.hehe")
                .ttl(10000)
                .maxLength(10).build();
    }

    @Bean
    TopicExchange springDLXNormalTopicExchange(){
        return ExchangeBuilder.topicExchange(RabbitMqConst.DLX_EX_NORMAL).durable(true).build();
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue springDLXQueue(){
        return QueueBuilder.durable(RabbitMqConst.DLX_Q_HUJIAN).build();
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    TopicExchange springDLXTopicExchange(){
        return ExchangeBuilder.topicExchange(RabbitMqConst.DLX_EX).durable(true).build();
    }

    @Bean
    Binding normalBind(@Qualifier("springDLXNormalTopicQueue") Queue queue,@Qualifier("springDLXNormalTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dlx.normal.#");
    }

    @Bean
    Binding dlxBind(@Qualifier("springDLXQueue") Queue queue,@Qualifier("springDLXTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("dlx.#");
    }

}