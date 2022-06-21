package com.hujian.spring;

import com.hujian.spring.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@ComponentScan
//@Configuration
public class MySpringApplication {
//    @Bean
//    public UserService userService(){
//        UserService service = new UserService();
//        service.setName("second hujian");
//        return service;
//    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MySpringApplication.class, args);
//        AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(MySpringApplication.class);
//        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
//        context.setAllowBeanDefinitionOverriding(true);
//        context.register(MySpringApplication.class);
//        context.addBeanFactoryPostProcessor(new UserService());
//        context.refresh();
//        context.refresh();
        UserService service = context.getBean("userService", UserService.class);
        service.test();

    }

}
