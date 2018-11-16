package com.zhejiang.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhejiang.manage.mapper")
public class TestApplication{

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }


}


