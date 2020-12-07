package com.hujian.rabbitmq.spring.consume;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @Classname DirectorCousumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
@RabbitListener(queues = {"springDirectQueue"})
public class DirectCousumer {
    @RabbitHandler
    public void process(String messages){
        System.out.println("消费了消息"+messages);
    }
}