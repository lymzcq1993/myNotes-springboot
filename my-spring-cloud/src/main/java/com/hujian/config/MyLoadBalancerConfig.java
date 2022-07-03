package com.hujian.config;

import com.hujian.loadbalance.MyLoaderBalanced;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hujian
 * @since 2022-07-01 16:57
 */
@Configuration
@LoadBalancerClient(name = "product-center",configuration = MyLoadBalancerConfig.class)
//可以通过注解指定多个负载的配置类
//@LoadBalancerClients(
//        value = {
//                @LoadBalancerClient(name = "product-center",configuration = MyLoadBalancerConfig.class )
//        }
//)
public class MyLoadBalancerConfig {
    @Bean
    public ReactorServiceInstanceLoadBalancer customLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider){
        return new MyLoaderBalanced(serviceInstanceListSupplierProvider);
    }
}
