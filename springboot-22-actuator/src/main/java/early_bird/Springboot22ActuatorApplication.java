package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
在 引入了 spring-boot-starter-actuator 后，可以使用SpringBoot为我们提供的准生产环境下的应用监控
	和管理功能。

步骤：
	引入 spring-boot-starter-actuator
	通过 http方式访问监控端点
	可进行shutdown （关闭当前应用 POST 提交，此端点默认关闭）

监控和管理端点：
	autoconfig：		所有自动配置信息
	beans：			所有Bean的信息
	configprops：	所有的配置属性
	dump：			线程状态信息
	env：			当前环境信息
	mappings：		应用 @RequestMapping 映射路径
	health：			应用健康状态
	metrics：		应用的各项指标
	trace：			追踪信息（最新的http请求）
	shutdown：		关闭当前应用（post请求， 默认关闭，如何开启？ endpoints.shutdown.enable=true）
	info：			当前应用信息

端点信息还可以定制:
	定制端点一般通过 endpoints+端点名+属性名来设置
		修改端点id：
			例如：修改 bean端点的id
			endpoints.beans.id = mybeans
		修改端点访问路径
			例如：修改 bean端点的访问路径 为 主机ip+端口+/b
			endpoins.beans.path = /b
		开启远程应用关闭功能
			endpoints.shutdown.enabled = true
		关闭所有的端点的访问
			endpoints.enable = false
		开启或关闭某个端点的访问：
			例如：开启或者关闭 beans 端点的访问
			endpoints.beans.enabled = true/false
		定制所有端点的访问根路径
			例如：设置根路径为： manage
			management.context-path = /manage
		设置端点的访问端口：
			例如：设置访问端口为 8222 （当设置为 -1 时，由于端口不存在，所以所有端点都无法访问）
			management.port = 8222

自定义健康状态指示器（health）
	1.编写一个指示器实现 HealthIndicator 接口
	2.指示器的名字必须为： XXXHealthIndicator
	3.加入到ioc容器中

*/
@SpringBootApplication
public class Springboot22ActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot22ActuatorApplication.class, args);
	}

}
