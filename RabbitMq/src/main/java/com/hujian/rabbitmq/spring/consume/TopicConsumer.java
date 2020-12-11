package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @Classname TopicConsumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
@RabbitListener(queues = {RabbitMqConst.TOPIC_Q_HUJIAN,RabbitMqConst.TOPIC_Q_HUJIAN2})
public class TopicConsumer {
    @RabbitHandler
    public void process(String messages){
        System.out.println("消费了消息"+messages);
    }
}