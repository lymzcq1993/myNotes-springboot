package rocketmq.service;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import rocketmq.RocketmqConst;

/**
 * @author hujian
 * @description
 * @date 2020/12/16 14:08
 */
@Component
@RocketMQMessageListener(consumerGroup = "basicCGroup", topic = RocketmqConst.BASIC_TOPIC)
public class SpringConsume implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt  msg) {
        System.out.println("接收到message"+new String(msg.getBody()));
    }
}
