package rocketmq.controller;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.RocketmqConst;

/**
 * @author hujian
 * @date 2022/5/31 13:52
 */
@RestController
@RequestMapping()
public class SendController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息的几种方式
     */
    @RequestMapping("/send/simpleMessgae")
    @GetMapping
    public String syncMessage(String message){
        System.out.println(rocketMQTemplate.getProducer().getProducerGroup());
        System.out.println(rocketMQTemplate.getProducer().getNamesrvAddr());
        System.out.println(rocketMQTemplate.getProducer().getDefaultTopicQueueNums());
        //普通消息发送
        rocketMQTemplate.convertAndSend(RocketmqConst.SIMPLE_TOPIC  ,message);
        //使用spring带tag的方式发送
        rocketMQTemplate.send(RocketmqConst.SIMPLE_TOPIC
                , MessageBuilder.withPayload(message)
                        //在此处设置tags
                        .setHeader(RocketMQHeaders.TAGS,"testTags")
                        .build());
        //异步发送消息
        rocketMQTemplate.asyncSend(RocketmqConst.SIMPLE_ASYNC_TOPIC
                , message
                //消息回调
                , new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("收到broker的返回结果");
                        System.out.println(sendResult);
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        System.out.println("收到broker的异常");
                        throwable.printStackTrace();
                    }
                });
        return "OK";
    }


    /**
     * 发送事务消息
     */
    @RequestMapping("transactionMessage")
    @GetMapping
    public String transactionMessage(String message){
        Message<String> stringMessage = MessageBuilder.withPayload(message)
                //事务监听器会接收该属性，不会接收tags
                .setHeader(RocketMQHeaders.TRANSACTION_ID,"TransID_0")
                //发到事务监听器里后,该tasg属性会失效
//                .setHeader(RocketMQHeaders.TAGS, RocketmqConst.TRANSACTION_TOPIC)
                .build();
        rocketMQTemplate.sendMessageInTransaction(RocketmqConst.TRANSACTION_TOPIC,stringMessage,null);
        return "OK";
    }
}
