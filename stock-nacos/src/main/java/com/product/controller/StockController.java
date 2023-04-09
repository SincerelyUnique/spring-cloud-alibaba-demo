package com.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Value("${server.port}")
    String port;

    @RequestMapping("/reduct")
    public String reduceProduct(){
        System.out.println("扣减库存: 请求端口是" + port);
        return "扣减库存: 请求端口是" + port;
    }
}
