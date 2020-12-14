package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author hujian
 * @Classname DirectorCousumer
 * @Description
 * @Date 2020/11/30 23:29
 */
@Component

public class DirectConsumer {
    @RabbitListener(queues = {RabbitMqConst.DIRECT_Q_HUJIAN})
    public void process(Message messages, Channel channel) throws InterruptedException, IOException {
        System.out.println("direct消费了消息"+new String(messages.getBody()));

        channel.basicAck(messages.getMessageProperties().getDeliveryTag(),false);
        //2：是否拒绝签收之前的所有消息   3：是否拒绝后将该消息重新放回队列
        //channel.basicNack(messages.getMessageProperties().getDeliveryTag(),false,true);
        Thread.sleep(500);
    }
}