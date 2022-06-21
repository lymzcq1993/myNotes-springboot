package com.hujian.spring.service;

/**
 * @author hujian
 * @since 2022-06-16 09:59
 */
//@Data
//@Component("userService")
public class UserService2 {
    private String name;

    private UserService2() {}

    //    public UserService2(){
//      this.name = "hujian";
//    };
    public void test(){
        System.out.println(name);
    }

}
