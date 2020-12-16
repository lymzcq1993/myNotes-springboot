package rocketmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketmq.RocketmqConst;
import rocketmq.service.SpringProducer;

/**
 * @author hujian
 * @description
 * @date 2020/12/16 13:52
 */
@RestController
@RequestMapping("basic")
public class ProducerController {
    @Autowired
    private SpringProducer producer;

    @RequestMapping("normal")
    public String normalMsg(String message){
        producer.sendMessage(RocketmqConst.BASIC_TOPIC+":TagsA",message);
        return "OK";
    }
}
