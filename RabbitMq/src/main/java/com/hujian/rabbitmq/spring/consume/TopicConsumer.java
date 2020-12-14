package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hujian
 * @Classname TopicConsumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
public class TopicConsumer  {
    @RabbitListener(queues = {RabbitMqConst.TOPIC_Q_HUJIAN2})
    public void processHUJIAN2(Message messages){
        System.out.println("topic hujian2消费了消息"+messages.getBody() );
    }

    @RabbitListener(queues = {RabbitMqConst.TOPIC_Q_HUJIAN})
    public void processHUJIAN(Message messages, Channel channel) throws IOException {
        System.out.println("topic  hujian消费了消息"+new String(messages.getBody()));
        System.out.println(channel.getDefaultConsumer());
    }

}