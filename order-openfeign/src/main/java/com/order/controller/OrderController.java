package com.order.controller;

import com.order.feign.ProductFeignService;
import com.order.feign.StockFeignService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    ProductFeignService productFeignService;

    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
        String message = stockFeignService.reduceProduct();
        String p = productFeignService.get(1);
        System.out.println(message);
        return message + "; " + p;
    }
}
