package com.order.feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service", path = "/product")
public interface ProductFeignService {
//    @RequestMapping("/{id}")
//    String get(@PathVariable("id") Integer id);

    @RequestLine("GET /{id}")
    String get(@Param("id") Integer id);
}
