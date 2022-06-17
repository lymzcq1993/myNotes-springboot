package rocketmq.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import rocketmq.RocketmqConst;

/**
 * @author hujian
 */
@Slf4j
@Service
@RocketMQMessageListener(
        //指定topic
        topic = RocketmqConst.SIMPLE_ASYNC_TOPIC
        //开启事务消息追踪
//        ,enableMsgTrace = true
//        ,customizedTraceTopic = "hujian-test-topic"
        //设置消费者组
        ,consumerGroup = "consumeGroup")
public class PushConsume implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("收到了tpoic:"+RocketmqConst.SIMPLE_ASYNC_TOPIC+"的消息："+message);
    }
}
