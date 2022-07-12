package com.hujian.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hujian
 * @since 2022-07-12 14:54
 */
@RestController
@RequestMapping("permit")
public class PermitController {

    @GetMapping("getUser")
    public String getUser(){
        return "123456";
    }
}
