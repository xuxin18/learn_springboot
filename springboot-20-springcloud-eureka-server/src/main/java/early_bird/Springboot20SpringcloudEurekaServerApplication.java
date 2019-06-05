package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/*
SpringCloud：是一个分布式的整体解决方案。Spring Cloud 为开发者提供了在 分布式系统中：
	配置管理
	服务发现
	熔断
	路由
	微代理
	控制总线
	一次性token
	全局锁
	leader选举
	分布式session
	集群状态
	等 快速构建的工具。
SpringCloud 分布式开发五大常用组件：
	服务发现： Netflix Eureka
	客服端负载均衡： Netflix Ribbon
	断路器： Netflix Hystrix
	服务网关： Netflix Zuul
	分布式配置： Spring Cloud Config

注册中心流程：
	1.配置 eureka 注册中心相关配置
	2.在Main类上开 使用 @EnableEurekaServer 注解

启动 注册中心后：
	访问 localhost:8761 （注册中心的主机地址 + 端口号）看看，注册中心是否启动了
*/

@EnableEurekaServer //启动 eureka 注册中心
@SpringBootApplication
public class Springboot20SpringcloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot20SpringcloudEurekaServerApplication.class, args);
	}

}
