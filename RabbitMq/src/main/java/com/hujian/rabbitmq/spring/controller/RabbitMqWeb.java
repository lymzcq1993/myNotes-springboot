package com.hujian.rabbitmq.spring.controller;

import com.hujian.rabbitmq.spring.config.RabbitMqConst;
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
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitMqConst.DIRECT_EX,RabbitMqConst.DIRECT_KEY,message+i);
        }
        return "OK";
    }

    @GetMapping("/fanout")
    public String sendFanoutMessage(String message){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(RabbitMqConst.FANOUT_EX,"",message+i);
        }
        return "OK";
    }

    @GetMapping("/topic")
    public String sendTopicMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMqConst.TOPIC_EX,"hujian.11","向hujian.11发送消息"+message);
        //rabbitTemplate.convertAndSend(RabbitMqConst.TOPIC_EX,"hujian2.22","向hujian2.11发送消息"+message);
        return "OK";
    }

    @GetMapping("/dlx")
    public String sendDLXMessage(String message){
        rabbitTemplate.convertAndSend(RabbitMqConst.DLX_EX_NORMAL,"dlx.normal.test","发送死信消息"+message);
        return "OK";
    }

    @GetMapping("/confirm")
    public String sendConfirmMessage(String message){
        /**
         *
         * @param correlationData
         * @param ack   是否被签收
         * @param cause  失败原因
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->{
                System.out.println("进入confirm方法");
                if(ack){
                    System.out.println("EXCHANGE接收成功"+cause);
                }
                else{
                    System.out.println("EXCHANGE接收成功接收失败"+cause);
                }
        });

        rabbitTemplate.setReturnCallback((message1, i, s, s1, s2) -> {

        });
        rabbitTemplate.convertAndSend(RabbitMqConst.DIRECT_EX+"ssss",RabbitMqConst.DIRECT_KEY,"向hujian.11发送消息"+message);
        return "OK";
    }
}