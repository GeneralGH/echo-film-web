package com.yiyu.echofilmspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.yiyu.echofilmspringboot.mapper")
public class EchoFilmSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoFilmSpringbootApplication.class, args);
    }

}
