package com.hujian.loadbalance;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hujian
 */
@Slf4j
public class MyLoaderBalanced {
//    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
//
//    public MyLoaderBalanced(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
//        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
//    }
//
//    @Override
//    public Mono<Response<ServiceInstance>> choose(Request request) {
//        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
//                .getIfAvailable(NoopServiceInstanceListSupplier::new);
//        return supplier.get(request).next()
//                .map(this::getInstacnce);
//    }
//
//    private Response<ServiceInstance> getInstacnce(List<ServiceInstance> instances){
//        System.out.println("自定义的均衡负载");
//        if(instances.isEmpty()){
//            return new EmptyResponse();
//        }
//        System.out.println(instances);
//        System.out.println("开始进行自定义的负载均衡");
//        // 随机算法
//        int size = instances.size();
//        Random random = new Random();
//        ServiceInstance instance = instances.get(random.nextInt(size));
//        log.info("选择的ip：port,{}:{}",instance.getUri(),instance.getPort());
//        return new DefaultResponse(instance);
//    }
////    @Bean
////    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment,
////                                                                   LoadBalancerClientFactory loadBalancerClientFactory){
////        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
////        new RandomL
////        return new RandomLoadBalancer(loadBalancerClientFactory
////                .getLazyProvider(name, ServiceInstanceListSupplier.class),
////                name);
////    }
}
