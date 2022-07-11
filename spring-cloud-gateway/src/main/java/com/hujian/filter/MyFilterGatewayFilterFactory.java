package com.hujian.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * 类名需要以为GatewayFilterFactory结尾
 * @author hujian
 */
@Slf4j
@Component
//也可以使用order进行排序
//@Order
public class MyFilterGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("调用MyFilterGatewayFilterFactory==="
                    + config.getName() + ":" + config.getValue());
            log.info("获取header:{}",exchange.getRequest().getHeaders().toString());
            return chain.filter(exchange);
        };
    }
}
