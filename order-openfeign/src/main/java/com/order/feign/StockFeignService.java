package com.order.feign;

import com.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//name指定调用rest接口所对应的服务名
//path指定调用rest接口所在的StockController指定的@RequestMapping
@FeignClient(name = "stock-service", path = "/stock")
//@FeignClient(name = "stock-service", path = "/stock", configuration = FeignConfig.class)
public interface StockFeignService {
    //声明需要调用的方法
    //类似mybatis底层通过动态代理实现的方式
    @RequestMapping("/reduct")
    String reduceProduct();
}
