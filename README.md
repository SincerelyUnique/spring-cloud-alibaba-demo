# spring-cloud-alibaba-demo


## 启动本地服务
docker-compose -f docker-compose-local-mysql.yml up -d
docker-compose -f docker-compose-local-nacos.yml up -d
docker-compose -f docker-compose-local-redis.yml up -d
docker-compose -f docker-compose-local-nginx.yml up -d
docker-compose -f docker-compose-local-minio.yml up -d


## 启动线上环境
docker-compose -f docker-compose-prod-redis.yml up -d
docker-compose -f docker-compose-prod-minio.yml up -d
docker-compose -f docker-compose-prod-mysql8.0.29.yml up -d
docker-compose -f docker-compose-prod-nacos.yml up -d


## 停止服务
docker-compose -f docker-compose.yml stop


## 停止并删除服务
docker-compose -f docker-compose.yml down
docker-compose -f docker-compose-service.yml down


## nacos
保护阈值：雪崩保护，0-1之间任意值，比如设0.5，健康实例/总实例<阈值，会把不健康的实例拿来用，防止雪崩，后面会根据sentinel做服务降级，不会用nacos来做雪崩保护
权重：结合负载均衡器 权重的机制，如ribbon权重值

```
C:\Users\Lenovo>netstat -ano | findstr "0"
```

## nacos集群环境搭建
伪集群，3个端口，需要mysql
1. 事先安装mysql，并执行conf下的mysql脚本建库建表
2. 编辑application.properties，改下端口8849，8850，8851
3. 编辑application.properties，使用外部数据源，编辑连接配置（注释打开改改就行）
4. 将conf\cluster.conf.example改为cluster.conf，编辑ip1:port1, ip2:port2, ip3:port3
5. 改bin\startup.sh，MODE=cluster，根据实际改jvm配置 
6. 改nginx，做upstream


## Ribbon均衡器
- 集群下才有负载均衡
- 两种负载均衡
  - 服务端负载均衡：硬件负载均衡（交换机-性能高）、软件负载均衡（nginx-省钱）
  - 客户端负载均衡：代码实现，提前把集群列表拉过来，自己决定采用轮询或者其他方式去访问
- 常见算法：随机、加权、轮询、hash（取模）、最小连接（压力小）
- IRule统一实现接口
  - RandomRule 随机
  - RoundRobinRule 轮询
  - RetryRule 重试（在轮询基础上），这台有问题请求那台，有超时限制
  - WeightedResponseTimeRule 权重，不是nacos的权重（所以两者有冲突，互斥），根据服务器请求时间来看，响应越快，权重越大
  - BestAvailableRule 找并发最小
  - ZoneAvoidanceRule 默认规则，区域就近（云环境）和服务可用性，轮询
- 修改默认的均衡方式
  - 基于配置类: 不能放在@ComponentScan能够扫描到的地方，否则所有服务共享
  - 基于配置文件application.yml，推荐这种方式，上面的有坑
- 自定义负载均衡
  - 实现IRule接口
  - 继承AbstractLoadBalancerRule类
  - 自己实现一定要注意并发问题
- 负载均衡懒加载：第一次调用的时候才去加载负载均衡器
  - 设置立即加载
- 使用LoadBalancer替换ribbon
  - RestTemplate
  - WebFlux
  - exclude从pom移除或禁用


## Feign调用
1. RestTemplate调用弊端
   - 维护调用url
   - HttpClient、Okhttp、HttpUrlConnection、WebClient（WebFlux）
   - Feign简化
2. Feign是Netffix，闭源了，Feign放在服务消费端，也是带负载均衡的，SpringCloud官方提供增强版OpenFeign
3. 像调用本地方法一样调用远程方法
4. 自定义配置-日志配置，四种日志级别，默认级别是不显示
   - 全局配置，作用所有服务
   - 局部配置
   - Feign接口对于注解要求比较严格，@PathVariable
5. 契约配置，还原为feign原生注解，为什么要还原feign原生注解？升级SpringCloud1.x并做兼容
6. 超时时间配置
7. 自定义拦截器，不是SpringMVC的拦截器
   - 附加日志
   - 添加附加headers（token认证一般从网关加）

