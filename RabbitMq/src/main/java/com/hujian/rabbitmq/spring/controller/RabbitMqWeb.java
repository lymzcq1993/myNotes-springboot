package com.hujian.rabbitmq.spring.controller;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 * @Classname RabbitMqWeb
 * @Description
 * @Date 2020/11/30 23:12
 */
@RestController
public class RabbitMqWeb {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/direct")
    public String sendDirectMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMqConst.DIRECT_EX,RabbitMqConst.DIRECT_KEY,message);
        return "OK";
    }

    @GetMapping("/fanout")
    public String sendFanoutMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMqConst.FANOUT_EX,"",message);
        return "OK";
    }

    @GetMapping("/topic")
    public String sendTopicMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMqConst.FANOUT_EX,"hujian.11","向hujian.11发送消息"+message);
        rabbitTemplate.convertAndSend(RabbitMqConst.FANOUT_EX,"hujian2.22","向hujian2.11发送消息"+message);
        return "OK";
    }

    @GetMapping("/confirm")
    public String sendConfirmMessage(String message){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData
             * @param ack   是否被签收
             * @param cause  失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("进入confirm方法");
                if(ack){
                    System.out.println("接收成功"+cause);
                }
                else{
                    System.out.println("接收失败"+cause);
                }
            }
        });
        rabbitTemplate.convertAndSend(RabbitMqConst.DIRECT_EX+"ssss",RabbitMqConst.DIRECT_KEY,"向hujian.11发送消息"+message);
        return "OK";
    }
}