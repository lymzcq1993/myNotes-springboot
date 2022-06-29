package com.hujian.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@AutoConfigureOrder(-212121)
//@ConditionalOnClass(name = {"com.hujian.spring.SpringAppConfig"})
//@Configuration(proxyBeanMethods = false)
//@ComponentScan
//@MapperScan
//@EnableAutoConfiguration
//@Configuration
public class MySpringApplication extends SpringBootServletInitializer
{
//    @Bean
//    @ConditionalOnMissingBean
//    public UserService userService(){
//        return  new UserService();
//    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MySpringApplication.class);
    }

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>()
        ConfigurableApplicationContext context = SpringApplication.run(MySpringApplication.class, args);
//        AnnotationConfigApplicationContext context  = new AnnotationConfigApplicationContext(MySpringApplication.class);
//        AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext();
//        context.setAllowBeanDefinitionOverriding(true);
//        context.register(MySpringApplication.class);
//        context.addBeanFactoryPostProcessor(new UserService());
//        context.refresh();
//        context.refresh();
//        UserService service = context.getBean("userService", UserService.class);
//        service.test();

    }

}
