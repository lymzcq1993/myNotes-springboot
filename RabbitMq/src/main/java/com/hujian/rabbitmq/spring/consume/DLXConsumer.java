package com.hujian.rabbitmq.spring.consume;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**死信接收5
 * @author hujian
 * @Classname DLXConsumer
 * @Date 2020/12/12 17:51
 */
@Component
public class DLXConsumer {
    @RabbitListener(queues = RabbitMqConst.DLX_Q_HUJIAN)
    public void dlxHandle(Message message, Channel channel) throws IOException, InterruptedException {
        System.out.println("接收到了死信队列的消息...."+new String(message.getBody()));

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        Thread.sleep(500);
    }
}