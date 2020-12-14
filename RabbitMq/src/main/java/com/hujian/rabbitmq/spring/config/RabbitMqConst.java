package com.hujian.rabbitmq.spring.config;

/**
 * @author hujian
 * @Classname RabbitMqConst
 * @Date 2020/12/11 11:44
 */
public class RabbitMqConst {
    public static final String DIRECT_EX = "spring.direct.ex";
    public static final String DIRECT_KEY = "direct.routKey";
    public static final String DIRECT_Q_HUJIAN = "spring.direct.queue.hujian";

    public static final String FANOUT_EX = "spring.fanout.ex";
    public static final String FANOUT_KEY = "spring.fanout.routKey";
    public static final String FANOUT_Q_HUJIAN = "spring.fanout.queue.hujian";
    public static final String FANOUT_Q_HUJIAN2 = "spring.fanout.queue.hujian2";
    public static final String FANOUT_Q_TTL = "spring.fanout.queue.ttl";

    public static final String TOPIC_EX = "spring.topic.ex";
    public static final String TOPIC_KEY = "spring.topic.routKey";
    public static final String TOPIC_Q_HUJIAN = "spring.topic.queue.hujian";
    public static final String TOPIC_KEY_HUJIAN = "hujian.*";
    public static final String TOPIC_Q_HUJIAN2 = "spring.topic.queue.hujian2";
    public static final String TOPIC_KEY_HUJIAN2 = "hujian2.*";

    /******************* 死信 *************************/
    public static final String DLX_EX = "spring.dlx.ex";
    public static final String DLX_Q_HUJIAN = "spring.dlx.queue.hujian";
    public static final String DLX_Q_NORMAL_HUJIAN = "spring.dlx.queue.normal.hujian";
    public static final String DLX_EX_NORMAL = "spring.dlx.normal.ex";

}