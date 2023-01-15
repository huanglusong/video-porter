package com.hhdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author huanghedidi
 * @Date 2023/1/15 23:05
 */
@SpringBootApplication
@Controller
public class VideoPorterApp {

    public static void main(String[] args) {
        SpringApplication.run(VideoPorterApp.class, args);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "hello!!!";
    }

}
