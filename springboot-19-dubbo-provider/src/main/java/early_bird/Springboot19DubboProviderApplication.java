package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
在分布式系统中，国内常用的是 zookeeper + dubbo 组合，而SpringBoot 推荐使用的是 Spring+SpringBoot+SpringCloud

zookeeper：是一个分布式的，开放源码的分布式应用程序协调服务。它是一个为分布式应用提供 一致性 服务的软件，提供的功能包括：
	配置维护
	域名服务
	分布式同步
	组服务
dubbo：是开源的分布式服务(RPC)框架，最大的特点是 按照分层的方式来架构，使用这种方式可以使各个层之间解耦合（或者最大限度的松耦合）
	从服务模型的角度来看，dubbo采用的是一种非常简单的模型：
		要么是提供方提供服务，要么是消费方消费服务
		所以基于这一点可以抽象出：
			服务提供方（provider）
			服务消费方（consumer）

dubbo 的工作原理：
	0.启动 dubbo 服务容器（此时会启动和加载 provider）
	1.provider在启动的时候会将自己提供的服务信息注册到 注册中心 （zookeeper）
	2.consumer在启动的时候会在 注册中心 中 订阅自己需要的服务
	3.注册中心 会以 异步的方式 将 consumer 需要的服务的地址列表返回给 consumer
		当 provider 发生变更时，注册中心 也会以 长连接 的方式（异步的）将变更推送给consumer
	4.consumer根据 地址列表的信息 来 同步的 调用 provider 提供的服务
	5.监控中心 Monitor 会以异步的方式来监控 consumer 和 provider

docker 中启动 zookeeper： docker run --name zk01 -p 2181:2181 --restart always -d zookeeper的镜像id

构建分布式服务的流程：
	将 provider 注册到注册中心 流程：
		引入dubbo 和 zkclient 相关依赖
		配置 dubbo 扫描包 和 注册中心地址
		使用 dubbo 的 @Service 注解 发布服务
*/
@SpringBootApplication
public class Springboot19DubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot19DubboProviderApplication.class, args);
	}

}
