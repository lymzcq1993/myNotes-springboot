package com.hujian.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 */
@Component
@Slf4j
public class MyConsumerListener {

    @KafkaListener(groupId = "twoGroup"
            ,topicPartitions = {
            @TopicPartition(topic = "twoTopic1",partitions = {"0","1"})
            ,@TopicPartition(topic = "twoTopic2",partitions = {"0"})
    })
    public void twoGroup(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        log.info("value:{}",value);
        log.info("record:{}",record);
        //手动提交
        ack.acknowledge();
    }


    @KafkaListener(groupId = "hujian1Group"
            ,topics = "hj-spring-topic")
    public void hujian1Group(ConsumerRecord<String, String> record, Acknowledgment ack){
        log.info("hujian1Group开始消费");
        String value = record.value();
        log.info("value:{}",value);
        log.info("record:{}",record);
        //手动提交
        ack.acknowledge();
    }

    @KafkaListener(groupId = "hujian2Group"
            ,topics = "hj-spring-topic")
    public void hujian2Group(ConsumerRecord<String, String> record, Acknowledgment ack){
        log.info("hujian2Group开始消费");
        String value = record.value();
        log.info("value:{}",value);
        log.info("record:{}",record);
        //手动提交
        ack.acknowledge();
    }
}
