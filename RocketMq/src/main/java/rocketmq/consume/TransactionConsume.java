package rocketmq.consume;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * @author hujian
 */
@RocketMQTransactionListener(
        //需要实现不同的事务逻辑可以指定不同的template来实现
        rocketMQTemplateBeanName = "rocketMQTemplate"
)
public class TransactionConsume implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //这个msg的实现类是GenericMessage，里面实现了toString方法
        //在Header中自定义的RocketMQHeaders.TAGS属性，到这里就没了。但是RocketMQHeaders.TRANSACTION_ID这个属性就还在。
        //而message的Header里面会默认保存RocketMQHeaders里的属性，但是都会加上一个RocketMQHeaders.PREFIX前缀
        Object transId = msg.getHeaders().get(RocketMQHeaders.PREFIX+RocketMQHeaders.TRANSACTION_ID);
        System.out.println(transId);
        System.out.println(msg.getPayload());
        System.out.println((arg));
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        System.out.println(msg.getPayload());
        return RocketMQLocalTransactionState.COMMIT;
    }
}
