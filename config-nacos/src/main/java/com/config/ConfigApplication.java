package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(ConfigApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigApplication.class, args);
        while (true){
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            System.out.println("user name is " + userName + " and user age is " + userAge);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
