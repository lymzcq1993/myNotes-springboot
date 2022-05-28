package com.hujian.redis;


import com.hujian.redis.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hujian
 * @Classname HelloController
 * @Description
 * @Date 2020/11/8 19:23
 */
@Controller
public class HelloController {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/index")
    public String hello(){
//        User u = userService.findUser(1L);
//        System.out.println(u.getName());
        redisTemplate.opsForValue().set("sdsd","dddd");
        return "index";
    }
}