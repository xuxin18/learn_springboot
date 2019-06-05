package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
将 consumer 从 注册中心引用服务的流程：
	引入依赖
	配置 dubbo 的注册中心地址
	引用服务
*/
@SpringBootApplication
public class Springboot19DubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot19DubboConsumerApplication.class, args);
	}

}
