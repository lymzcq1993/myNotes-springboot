package rocketmq.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hujian
 */
@Slf4j
@Service
public class PullConsume {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 也可以通过配置额外的RocketmqTemplate来拉取不同topic消息
     */
    public void receive(){
        List<String> receive = rocketMQTemplate.receive(String.class);
        for (String s : receive) {
            System.out.println(receive);
        }
    }
}
