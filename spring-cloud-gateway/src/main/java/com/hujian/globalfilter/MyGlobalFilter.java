package com.hujian.globalfilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author hujian
 */
@Component
@Slf4j
//使用order进行过滤器排序
//@Order
public class MyGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求中的token，可以在此进行鉴权
        List<String> token = exchange.getRequest().getHeaders().get("auth");
        log.info("token:"+ token);
        if (token == null || token.isEmpty()){
            return null;
        }
        return chain.filter(exchange);
    }
}
