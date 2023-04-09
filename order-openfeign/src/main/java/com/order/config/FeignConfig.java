package com.order.config;

import feign.Contract;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//当使用@Configuration会将配置作用与全局服务
//如果只想要配置某个服务就不要加
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * 修改契约配置，支持Feign原生的注解
     * @return
     */
//    @Bean
//    public Contract feignContract(){
//        return new Contract.Default();
//    }

    /**
     * fei超时时间设置
     * @return
     */
//    @Bean
//    public Request.Options options(){
//        return new Request.Options(1000, 2000);
//    }


}
