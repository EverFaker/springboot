package com.zhejiang.manage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author durantjiang
 */
@RestController
@RequestMapping("/hello")
public class IndexController {

    @RequestMapping("")
    public String index()  {
        return "index";
    }
}

