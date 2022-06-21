package com.hujian.spring.service;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @since 2022-06-16 09:59
 */
@Data
@Component
public class UserService implements BeanDefinitionRegistryPostProcessor {
    private String name;
    private String hobby;
    public UserService(String name){
        this.name = name;
    }

//    @Autowired
    public UserService(String name,String hobby){
        this.name = name;
        this.hobby = hobby;
    }

    public UserService(){
      this.name = "hujian";
    };
    public void test(){
        System.out.println(name);
    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
