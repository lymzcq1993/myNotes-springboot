package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @Classname FanoutCousumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
public class FanoutConsumer {
    @RabbitListener(queues = {RabbitMqConst.FANOUT_Q_HUJIAN})
    public void process(Message message){
        System.out.println("消费了消息"+message.getBody());
    }
}