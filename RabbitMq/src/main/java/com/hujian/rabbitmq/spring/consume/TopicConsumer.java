package com.hujian.rabbitmq.spring.consume;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @Classname TopicConsumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
public class TopicConsumer  {
    //@RabbitListener(queues = {RabbitMqConst.TOPIC_Q_HUJIAN2})
    public void processHUJIAN2(String messages){
        System.out.println("消费了消息"+messages);
    }

    //@RabbitListener(queues = {RabbitMqConst.TOPIC_Q_HUJIAN})
    public void processHUJIAN(Message messages, Channel channle){
        System.out.println("消费了消息"+messages.getBody());
        System.out.println(channle);
    }

}