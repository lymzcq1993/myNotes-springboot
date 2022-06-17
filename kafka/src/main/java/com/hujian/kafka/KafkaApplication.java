package com.hujian.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public NewTopic initTopic(){
        //topic名称，分区数，副本数
        return new NewTopic("hj-spring-topic",8,(short)1);
    }

    @Bean
    public KafkaAdmin.NewTopics initTopics(){
        return new KafkaAdmin.NewTopics(
                new NewTopic("twoTopic1",4,(short)1),
                new NewTopic("twoTopic2",4,(short)1)
        );
    }
}
