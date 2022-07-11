package com.hujian.predicates;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言需要遵守规范
 * 1. 类名需以RoutePredicateFactory结尾
 * 2. 使用自定义工厂的时候必须是类名开头的用法，也就是[xxxx]RoutePredicateFactory,实际是用xxxx来使用
 * @author hujian
 */
@Component
@Slf4j
public class CheckNameRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckNameRoutePredicateFactory.Config> {
    public CheckNameRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            //判断header中是否有自定义设置的值
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
                List<String> auth = headers.get("auth");
                if (auth == null){
                    return  false;
                }
                log.info("使用CheckNameRoutePredicateFactory" + config.getName());
                return auth.contains(config.getName());
            }
        };
    }

    /**
     * 快捷配置,可以在yml中直接使用-CheckName=xxx这样的方式配置
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("name");
    }

    @Data
    public static class Config {
        private String name;
    }
}
