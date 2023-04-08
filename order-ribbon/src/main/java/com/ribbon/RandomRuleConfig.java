package com.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomRuleConfig {
    @Bean
    //方法名一定要叫这个
    public IRule iRule(){
        return new RandomRule();
    }
}
