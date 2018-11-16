package com.zhejiang.manage.controller;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.User;
import com.zhejiang.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author durantjiang
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> helloWorld(){
       return userService.selectByParams(new Criteria());
    }
}
