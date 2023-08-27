package com.caicai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //springbooot 应用
@MapperScan("com.caicai.mapper")
public class newsApplication {
    public static void main(String[] args) {
        SpringApplication.run(newsApplication.class);
    }
}
