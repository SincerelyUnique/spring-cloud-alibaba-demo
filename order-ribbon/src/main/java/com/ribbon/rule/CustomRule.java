package com.ribbon.rule;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class CustomRule extends AbstractLoadBalancerRule {
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    public Server choose(Object key) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        //获得当前请求的服务的实例
        List<Server> reachableServers = loadBalancer.getReachableServers();
        int random = ThreadLocalRandom.current().nextInt(reachableServers.size());
        Server server = reachableServers.get(random);
//        if (server.isAlive()){
//            return server;
//        }
        return server;
    }
}
