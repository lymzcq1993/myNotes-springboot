package com.hujian.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 * @since 2022-05-20 11:50
 */
@Controller
@RestController
@RequestMapping("kafka")
public class KafkaController {
    private final static String TOPIC_NAME = "hj-spring-topic";
    private final static String TOPIC_TWO_NAME1 = "twoTopic1";
    private final static String TOPIC_TWO_NAME2 = "twoTopic2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/send")
    public String send(String message) {
        //指定分区
//        kafkaTemplate.send(TOPIC_NAME, 0, "key", message);
        //不指定分区指定key，根据key的哈希来存储分区
        kafkaTemplate.send(TOPIC_NAME,  "key", message);


        kafkaTemplate.send(TOPIC_TWO_NAME1, message);
        kafkaTemplate.send(TOPIC_TWO_NAME2, message);
        return "OK";
    }
}
