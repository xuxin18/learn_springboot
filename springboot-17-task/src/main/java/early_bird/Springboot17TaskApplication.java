package early_bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
异步任务：
	1.在main类上加上 @EnableAsync 开启异步注解功能
	2.在异步方法上加上 @Async 注解 标注该方法是一个异步方法
定时任务：
	1.在main类上加上 @EnableScheduling 开启定时注解功能
	2.在定时方法上加上 @Scheduled 注解 标注该方法是一个定时方法
邮件任务：
	1.导入依赖 spring-boot-starter-mail
	2.配置邮件服务器地等相关配置
	3.使用 JavaMailSenderImpl 来操作邮件相关功能
*/
@EnableAsync  //开启异步注解功能
@EnableScheduling //开启定时注解功能
@SpringBootApplication
public class Springboot17TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot17TaskApplication.class, args);
	}

}
