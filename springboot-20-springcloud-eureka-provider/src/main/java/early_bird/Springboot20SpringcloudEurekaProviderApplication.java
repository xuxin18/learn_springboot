package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
更改 server.port可以打成多个 jar 包，然后同时运行。在注册中心中可以发现启动了多个provider
*/
@SpringBootApplication
public class Springboot20SpringcloudEurekaProviderApplication {

	/*
		启动后 ，访问 服务提供者的主机地址+ 端口号 + 服务名 （http://localhost:8081/ticket ），能够正常访问
	*/
	public static void main(String[] args) {
		SpringApplication.run(Springboot20SpringcloudEurekaProviderApplication.class, args);
	}

}
