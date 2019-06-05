package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
consumer 发现服务的步骤：
	1.配置当前应用名 和 eureka 相关配置
	2.在 main 类上 添加 @EnableDiscoveryClient 注解
	3.由于eureka 使用的是 轻量级的 http协议进行通讯，所以需要 给 ioc 容器中 注入 RestTemplate
*/
@EnableDiscoveryClient //开启 consumer 的 发现服务功能
@SpringBootApplication
public class Springboot20SpringcloudEurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot20SpringcloudEurekaConsumerApplication.class, args);
	}

	@LoadBalanced // 开启负载均衡,如果同一个服务有多个provider，则轮循请求不同的provider
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
