package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hujian
 * @Classname FanoutCousumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component
public class FanoutConsumer {
    @RabbitListener(queues = {RabbitMqConst.FANOUT_Q_HUJIAN})
    public void process(Message message, Channel channel) throws InterruptedException, IOException {
        System.out.println("fanout消费了消息"+new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        Thread.sleep(500);
    }

    @RabbitListener(queues = {RabbitMqConst.FANOUT_Q_TTL})
    public void processTTL(Message message,Channel channel) throws IOException, InterruptedException {
        System.out.println("fanout消费了TTL消息"+new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        Thread.sleep(500);
    }
}