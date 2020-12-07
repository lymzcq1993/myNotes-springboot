package com.hujian.rabbitmq.spring.controller;

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

    @GetMapping("/rbmq")
    public String sendDirectMessage(){
        rabbitTemplate.convertAndSend("springDirectExchange","springTestKey","测试用的消息");
        return "OK";
    }
}