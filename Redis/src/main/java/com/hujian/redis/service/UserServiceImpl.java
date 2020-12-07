package com.hujian.redis.service;

import com.hujian.redis.dao.UserMapper;
import com.hujian.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hujian
 * @Classname UserServiceImpl
 * @Description
 * @Date 2020/11/8 19:46
 */
@Service
public class UserServiceImpl {
    @Autowired
    private UserMapper dao;

    public User findUser(Long id){
        return dao.selectByPrimaryKey(id);
    }
}