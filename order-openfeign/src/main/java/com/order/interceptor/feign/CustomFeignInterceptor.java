package com.order.interceptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void apply(RequestTemplate template) {
        template.header("xxx", "xxxx");
        template.query("id", "9");
        template.uri("/9");
        logger.info("feign拦截器");
    }
}
