package com.hujian.spring.service;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author hujian
 * @since 2022-06-16 09:59
 */
@Data
@Component
public class UserService {
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
        System.out.println(this);
    }

}
